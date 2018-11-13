package com.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class TreesSetEx {

	public static void main(String[] args) {

		String[] itgArray = new String[] { "16 WW", "1D WW", "1N WW", "21 WW", "2A WW", "2B WW", "2C WW", "2D WW",
				"2G WW", "2H WW", "2N WW", "2P WW", "2Q WW", "30 WW", "3Y WW", "4H WW", "4L WW", "4M WW", "4V WW",
				"4X WW", "52 WW", "5D WW", "5M WW", "5S WW", "5T WW", "5U WW", "5X WW", "63 WW", "64 WW", "65 WW",
				"67 WW", "6A WW", "6B WW", "6J WW", "6Q WW", "6U WW", "6V WW", "6X WW", "7B WW", "7F WW", "7T WW",
				"8A WW", "8J WW", "8N WW", "8W WW", "91 WW", "9C WW", "9F WW", "9G WW", "9H WW", "9J WW", "9R WW",
				"9S WW", "9T WW", "A5 WW", "AK WW", "AN WW", "AU WW", "B7 WW", "BO WW", "BQ WW", "C5 WW", "CY WW",
				"DA WW", "DG WW", "DU WW", "DY WW", "E0 WW", "E4 WW", "E5 WW", "ED WW", "EE WW", "EO WW", "EZ WW",
				"F2 WW", "F4 WW", "F7 WW", "F8 WW", "FB WW", "FD WW", "FF WW", "FG WW", "FH WW", "FO WW", "FW WW",
				"G0 WW", "G7 WW", "G8 WW", "G9 WW", "GA WW", "GB WW", "GC WW", "GD WW", "GE WW", "GF WW", "GG WW",
				"GH WW", "GI WW", "GJ WW", "GK WW", "GL WW", "GM WW", "GN WW", "GO WW", "GP WW", "GQ WW", "GR WW",
				"GS WW", "GT WW", "GU WW", "GV WW", "GW WW", "GX WW", "GY WW", "GZ WW", "HQ WW", "I0 WW", "I1 WW",
				"I2 WW", "IA WW", "IB WW", "ID WW", "IE WW", "IF WW", "IG WW", "IK WW", "IL WW", "IQ WW", "IR WW",
				"IS WW", "IT WW", "IU WW", "IV WW", "IW WW", "IX WW", "IY WW", "JP WW", "JZ WW", "K2 WW", "K4 WW",
				"K5 WW", "K6 WW", "K7 WW", "K8 WW", "KV WW", "LY WW", "M3 WW", "MA WW", "MC WW", "MF WW", "MG WW",
				"MK WW", "ML WW", "MN WW", "MP WW", "MQ WW", "PQ WW", "R4 WW", "R6 WW", "R7 WW", "SB WW", "ST WW",
				"T2 WW", "T4 WW", "TA WW", "TB WW", "TW WW", "TX WW", "TY WW", "UD WW", "UI WW", "UK WW", "UN WW",
				"UO WW", "UR WW", "US WW", "UT WW", "UV WW", "VG WW", "VI WW", "WS WW" };

		String[] excel = new String[] { "16 WW", "21 WW", "52 WW", "1M WW", "2C WW", "2G WW", "2H WW", "5U WW", "5X WW",
				"67 WW", "6J WW", "6U WW", "6V WW", "7F WW", "7S WW", "8J WW", "8N WW", "8W WW", "9F WW", "9G WW",
				"9H WW", "9J WW", "9R WW", "9S WW", "9T WW", "AN WW", "BO WW", "BQ WW", "CY WW", "DG WW", "DY WW",
				"EZ WW", "FB WW", "FD WW", "FF WW", "FG WW", "FH WW", "G7 WW", "GA WW", "GB WW", "GG WW", "GH WW",
				"GU WW", "GV WW", "I0 WW", "I1 WW", "KV WW", "MF WW", "MG WW", "MN WW", "MP WW", "R7 WW", "TA WW",
				"TB WW", "US WW", "UT WW", "UV  WW", "30 WW", "5S WW", "63 WW", "6B WW", "7B WW", "DA WW", "GE WW",
				"GF WW", "ID WW", "IE WW", "JP WW", "K2 WW", "ML WW", "TW WW", "TX WW", "TY WW", "UK WW", "UN WW",
				"UO WW", "UR WW", "1D WW", "1N WW", "2A WW", "2B WW", "2N WW", "2Q WW", "4H WW", "5M WW", "7T WW",
				"A5 WW", "AU WW", "B7 WW", "DU WW", "GC WW", "GD WW", "GP WW", "IF WW", "UI WW", "GI WW", "IA WW",
				"IB WW", "VG WW", "VI WW", "VJ WW", "VK WW", "5D WW", "FL WW", "HQ WW", "M3 WW", "MK WW", "MQ WW",
				"R4 WW", "WS WW", "6A WW", "19 WW", "2D WW", "2P WW", "3Y WW", "4L WW", "4M WW", "4V WW", "4X WW",
				"5T WW", "64 WW", "65 WW", "6Q WW", "8A WW", "91 WW", "9C WW", "C5 WW", "E0 WW", "E4 WW", "E5 WW",
				"ED WW", "EE WW", "EO WW", "F2 WW", "F4 WW", "F7 WW", "F8 WW", "FO WW", "FW WW", "G0 WW", "G8 WW",
				"G9 WW", "GJ WW", "GK WW", "GL WW", "GM WW", "GN WW", "GO WW", "GQ WW", "GR WW", "GS WW", "GT WW",
				"GW WW", "GX WW", "GY WW", "GZ WW", "HF WW", "I2 WW", "IG WW", "IM WW", "K4 WW", "K6 WW", "K7 WW",
				"K8 WW", "MA WW", "MC WW", "PQ WW", "R6 WW", "UD WW", "HF WW", "I0 WW", "I1 WW", "IA WW", "IB WW",
				"ID WW", "IE WW", "IF WW", "IG WW", "IT WW", "IS WW", "IU WW", "IR WW", "IV WW", "IY WW", "IZ WW",
				"IW WW", "IX WW", "JZ WW", "IL WW", "IK WW", "IQ WW" };
		ArrayList<String> itgList = new ArrayList<>(Arrays.asList(itgArray));
		ArrayList<String> excelList = new ArrayList<>(Arrays.asList(excel));
		System.out.println(itgList.size() + "," + excelList.size());
		Collections.sort(excelList);
		//excelList.retainAll(itgList);
		excelList.removeAll(itgList);
		System.out.println(excelList);
		
		System.out.println(itgList);

		TreeSet<String> itgTree = new TreeSet<>(itgList);
		System.out.println(itgTree);
		System.out.println(itgTree.size());

	}

}

