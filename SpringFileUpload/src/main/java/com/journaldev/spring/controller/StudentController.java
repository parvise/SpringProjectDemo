package com.journaldev.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.springmvc.enumConst.LanguageSelection;
import com.journaldev.spring.jms.SingetonCache;
import com.journaldev.spring.model.DBFiles;
import com.journaldev.spring.model.Student;
import com.journaldev.spring.model.StudentBean;
import com.journaldev.spring.service.StudentService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Controller
public class StudentController {

	private StudentService personService;
	private boolean isEdit = false;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	public void setPersonService(StudentService ps) {
		this.personService = ps;
	}

	@ModelAttribute
	public void addCommon(Model model) {
		model.addAttribute("commandName", "student");
		model.addAttribute("message", "Student");
		model.addAttribute("languages", LanguageSelection.values());
	}

	@RequestMapping(value = "/changeLanguage", method = RequestMethod.GET)
	public String changeLanguage(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		System.out.println("Locale-->" + LocaleContextHolder.getLocale().getLanguage());
		/*System.out
				.println("msg--->" + messageSource.getMessage("mymessage.name", null, LocaleContextHolder.getLocale()));*/
		System.out.println("Languaf*****" + request.getParameter("siteLanguage"));

		HttpSession session = request.getSession();
		StudentBean bean = (StudentBean) session.getAttribute("student");

		if (bean == null)
			model.addAttribute("student", new StudentBean());
		else
			model.addAttribute("student", bean);

		model.addAttribute("listPersons", getList(response));

		return "/person";
	}

	@RequestMapping(value = "/person/student", method = RequestMethod.GET)
	public String load(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		System.out.println("Student Details Loaded");
		model.addAttribute("addAction", "/person/student/add");

		HttpSession session = request.getSession();
		StudentBean bean = (StudentBean) session.getAttribute("student");

		if (isEdit) {
			StudentBean student = (StudentBean) session.getAttribute("EditDetails");
			model.addAttribute("EditDetails", student);
		}
		isEdit = false;
		if (bean == null)
			model.addAttribute("student", new StudentBean());
		else
			model.addAttribute("student", bean);

		model.addAttribute("listPersons", getList(response));
		return "person";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listPersons(Model model, HttpServletResponse response) throws IOException, SQLException {
		model.addAttribute("addAction", "/person/student/add");
		model.addAttribute("message", "Student");
		model.addAttribute("person", new StudentBean());
		model.addAttribute("listPersons", getList(response));
		return "person";
	}

	// For add and update person both
	@RequestMapping(value = "/person/student/add", method = RequestMethod.POST)
	public String addPerson(Model model, @ModelAttribute("person") StudentBean p, HttpServletRequest request)
			throws IOException {
		System.out.println("I am Person controller Add Button clicked " + p);
		HttpSession session = request.getSession();
		
		String btnValue = request.getParameter("btnValue");
		if (btnValue.equalsIgnoreCase("Back")) {
			model.addAttribute("EditDetails", new StudentBean());
			// model.addAttribute("listPersons", list);

			session.setAttribute("EditDetails", new StudentBean());
			return "redirect:/person/student";
		}

		Student student = convertBeanToEntity(p);

		CacheManager manager = SingetonCache._getInstance();
		Cache cache = manager.getCache("listStudents");
		cache.removeAll();

		if (p.getId() == 0) {
			// new person, add it
			this.personService.addStudent(student);
		} else {
			// existing person, call update
			this.personService.updateStudent(student);
			session.setAttribute("person", student);
		}

		return "redirect:/person/student";

	}

	@RequestMapping("/remove/Student/{id}")
	public String removePerson(@PathVariable("id") int id) {
		System.out.println("Delete Link clciked " + id);
		this.personService.removeStudent(id);
		return "redirect:/person/student";
	}

	@RequestMapping("/edit/Student/{id}")
	public ModelAndView editPerson(@PathVariable("id") int id, Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		isEdit = true;
		System.out.println("Edit link clicked");
		Student entity = personService.getStudentById(id);
		StudentBean bean = convertEntityToBean(entity, response);
		// List<Student> list = personService.listStudents();

		HttpSession session = request.getSession();
		model.addAttribute("EditDetails", bean);
		// model.addAttribute("listPersons", list);

		session.setAttribute("EditDetails", bean);
		// session.setAttribute("listPersons", list);

		return new ModelAndView("redirect:/person/student", "model", session);
	}

	@RequestMapping("/Student/generateXL")
	public @ResponseBody String generateXLReport() throws IOException {
		System.out.println("Welcome XL");

		// Create a Workbook
		XSSFWorkbook excel = new XSSFWorkbook(); // .xlsx
		// HSSFWorkbook .xls

		/*
		 * CreationHelper helps us create instances of various things like
		 * DataFormat, Hyperlink, RichTextString etc, in a format (HSSF, XSSF)
		 * independent way
		 */
		CreationHelper helper = excel.getCreationHelper();

		// Create a Sheet
		Sheet sheet = excel.createSheet("Student");

		// Create a Font for styling header cells
		XSSFFont headerFont = excel.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.GREEN.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = excel.createCellStyle();
		headerCellStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		String[] columns = { "Id", "Name", "Country", "DOB" };
		// Create cells
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		// Create Cell Style for formatting Date
		CellStyle dateCellStyle = excel.createCellStyle();
		dateCellStyle.setDataFormat(helper.createDataFormat().getFormat("dd-MM-yyyy"));

		List<Student> list = this.personService.listStudents();

		int row = 1;
		for (Student student : list) {
			Row rowText = sheet.createRow(row++);
			rowText.createCell(0).setCellValue(student.getId());
			rowText.createCell(1).setCellValue(student.getName());
			rowText.createCell(2).setCellValue(student.getCountry());
			Cell dateCell = rowText.createCell(3);
			dateCell.setCellValue(new Date());
			dateCell.setCellStyle(dateCellStyle);
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(
				"C:/Users/parvisecp11788/Downloads/SpringProjects/SpringMVCHibernate/Student-List.xlsx");
		excel.write(fileOut);
		fileOut.close();

		// Closing the workbook
		excel.close();
		System.out.println("Done");
		return "Success";
	}

	private Student convertBeanToEntity(StudentBean bean) throws IOException {
		Student student = new Student();

		student.setId(bean.getId());
		student.setName(bean.getName());
		student.setCountry(bean.getCountry());

		DBFiles dbFile = new DBFiles();
		if (bean.getFile() != null) {
			dbFile.setId(bean.getFileId());
			dbFile.setFileType(bean.getFile().getContentType());
			dbFile.setFileName(bean.getFile().getOriginalFilename());
			dbFile.setFileBytes(bean.getFile().getBytes());
			// dbFile.setContent(bean.getFile().getBytes());
			dbFile.setDescription(bean.getDescription());
			dbFile.setStudent(student);

			try {

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				String url = "C:/Users/parvisecp11788/Downloads/SpringProjects/SpringFileUpload/src/main/resources/images";
				// String url = "C:/Users/parvisecp11788/images";
				File dir = new File(url);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(
						dir.getAbsolutePath() + File.separator + bean.getFile().getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(dbFile.getFileBytes());
				stream.close();
				System.out.println("Server File Location=" + serverFile.getAbsolutePath());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		Set<DBFiles> set = new HashSet<DBFiles>();
		set.add(dbFile);
		student.setStudentDocuments(set);

		return student;
	}

	private StudentBean convertEntityToBean(Student entity, HttpServletResponse response)
			throws IOException, SQLException {
		StudentBean student = new StudentBean();

		student.setId(entity.getId());
		student.setName(entity.getName());
		student.setCountry(entity.getCountry());
		Set<DBFiles> dbFiles = entity.getStudentDocuments();
		for (DBFiles dbFiles2 : dbFiles) {
			byte[] bytes = null;
			if (dbFiles2.getContent() != null) {
				Blob blob = dbFiles2.getContent();
				bytes = blob.getBytes(1l, (int) blob.length());
				student.setFileBytes(bytes);
			}
			student.setFileId(dbFiles2.getId());
			student.setFileName(dbFiles2.getFileName());
			student.setDescription(dbFiles2.getDescription());
			if (isEdit) {
				student.setImgUrl(dbFiles2.getFileName());
			}
		}

		return student;
	}

	private List<StudentBean> getList(HttpServletResponse response) throws IOException, SQLException {
		List<Student> entityList = this.personService.listStudents();
		List<StudentBean> studentList = new ArrayList<StudentBean>();
		for (Student student : entityList) {
			studentList.add(convertEntityToBean(student, response));
		}

		CacheManager manager = SingetonCache._getInstance();
		Cache cache = manager.getCache("listStudents");
		if (cache == null) {
			manager.addCache("listStudents");
		}
		cache.removeAll();
		cache = manager.getCache("listStudents");
		if (!cache.isKeyInCache("All")) {
			cache.put(new Element("All", studentList));
			return studentList;
		}

		Element element = cache.get("All");
		Object value = element.getObjectValue();

		return (List<StudentBean>) value;
	}

}
