package com.journaldev.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.springmvc.enumConst.LanguageSelection;
import com.journaldev.spring.model.DBFiles;
import com.journaldev.spring.model.Employee;
import com.journaldev.spring.model.EmployeeBean;
import com.journaldev.spring.service.EmployeeService;

@Controller
public class EmployeeController {

	private EmployeeService personService;
	private boolean isEdit = false;

	@Autowired
	public void setPersonService(EmployeeService ps) {
		this.personService = ps;
	}

	@ModelAttribute
	public void addCommon(Model model) {
		model.addAttribute("commandName", "employee");
		model.addAttribute("message", "Employee");
		model.addAttribute("languages", LanguageSelection.values());
	}

	@RequestMapping(value = "/person/employee", method = RequestMethod.GET)
	public String load(Model model, HttpServletRequest request) throws SQLException {
		System.out.println("Employee Link Clicked");
		model.addAttribute("addAction", "/person/employee/add");
		HttpSession session = request.getSession();
		EmployeeBean bean = (EmployeeBean) session.getAttribute("employee");

		if (isEdit) {
			EmployeeBean employee = (EmployeeBean) session.getAttribute("EditDetails");
			model.addAttribute("EditDetails", employee);
		}
		isEdit = false;

		if (bean == null)
			model.addAttribute("employee", new EmployeeBean());
		else
			model.addAttribute("employee", bean);
		model.addAttribute("listPersons", getList());

		return "person";
	}

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String listEmployees(Model model) throws SQLException {
		model.addAttribute("bean", new Employee());
		model.addAttribute("list", getList());
		return "person";
	}

	// For add and update person both
	@RequestMapping(value = "/person/employee/add", method = RequestMethod.POST)
	public String addPerson(Model model, @ModelAttribute("person") EmployeeBean p, HttpServletRequest request)
			throws IOException {
		System.out.println("I am employee controller" + p);

		HttpSession session = request.getSession();

		Employee employee = convertBeanToEntity(p);

		if (p.getId() == 0) {
			// new person, add it
			this.personService.addEmployee(employee);
		} else {
			// existing person, call update
			this.personService.updateEmployee(employee);
			session.setAttribute("person", employee);
		}

		return "redirect:/person/employee";

	}

	@RequestMapping("/remove/Employee/{id}")
	public String removePerson(@PathVariable("id") int id) {
		this.personService.removeEmployee(id);
		return "redirect:/person/employee";
	}

	@RequestMapping("/edit/Employee/{id}")
	public ModelAndView editPerson(@PathVariable("id") int id, Model model, HttpServletRequest request)
			throws SQLException {
		isEdit = true;
		System.out.println("Edit link clicked");
		Employee entity = personService.getEmployeeById(id);
		// List<Employee> list = personService.listEmployees();

		EmployeeBean employee = convertEntityToBean(entity);

		HttpSession session = request.getSession();
		model.addAttribute("EditDetails", employee);
		// model.addAttribute("listPersons", list);

		session.setAttribute("EditDetails", employee);

		// session.setAttribute("listPersons", list);
		return new ModelAndView("redirect:/person/employee", "model", session);
	}

	@RequestMapping("/Employee/generateXL")
	public ResponseEntity<HttpStatus> generateXLReport() {
		System.out.println("Welcome XL");
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	private Employee convertBeanToEntity(EmployeeBean bean) throws IOException {
		Employee employee = new Employee();

		employee.setId(bean.getId());
		employee.setName(bean.getName());
		employee.setCountry(bean.getCountry());

		DBFiles dbFile = new DBFiles();
		if (bean.getFile() != null) {
			dbFile.setId(bean.getFileId());
			dbFile.setFileType(bean.getFile().getContentType());
			dbFile.setFileName(bean.getFile().getOriginalFilename());
			dbFile.setFileBytes(bean.getFile().getBytes());
			// dbFile.setContent(bean.getFile().getBytes());
			dbFile.setDescription(bean.getDescription());
			dbFile.setEmployee(employee);
		}
		Set<DBFiles> set = new HashSet<DBFiles>();
		set.add(dbFile);
		employee.setEmployeeDocuments(set);
		return employee;
	}

	private EmployeeBean convertEntityToBean(Employee entity) throws SQLException {
		EmployeeBean employee = new EmployeeBean();

		employee.setId(entity.getId());
		employee.setName(entity.getName());
		employee.setCountry(entity.getCountry());
		Set<DBFiles> dbFiles = entity.getEmployeeDocuments();
		for (DBFiles dbFiles2 : dbFiles) {
			byte[] bytes = null;
			if (dbFiles2.getContent() != null) {
				Blob blob = dbFiles2.getContent();
				bytes = blob.getBytes(1l, (int) blob.length());
				employee.setFileBytes(bytes);
			}
			employee.setFileName(dbFiles2.getFileName());
			employee.setDescription(dbFiles2.getDescription());
			employee.setFileId(dbFiles2.getId());
			if (isEdit) {
				try {

					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
					File dir = new File(rootPath + File.separator + "Employee");
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + dbFiles2.getFileName());
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					employee.setImgUrl(serverFile.getAbsolutePath());
					System.out.println("Server File Location=" + serverFile.getAbsolutePath());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return employee;
	}

	private List<EmployeeBean> getList() throws SQLException {
		List<Employee> entityList = this.personService.listEmployees();
		List<EmployeeBean> employeeList = new ArrayList<EmployeeBean>();
		for (Employee student : entityList) {
			employeeList.add(convertEntityToBean(student));
		}
		return employeeList;
	}

}
