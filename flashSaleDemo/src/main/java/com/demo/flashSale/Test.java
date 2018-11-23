package com.demo.flashSale;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Test {

	public static void main(String[] args) {
		String[] test = new String[] { "30", "5S", "63", "6B", "7B", "DA", "GE", "GF", "ID", "IE", "JP", "K2", "ML",
				"TW", "TX", "TY", "UK", "UN", "UO", "UR", "1D", "1N", "2A", "2B", "2N", "2Q", "4H", "5M", "7T", "A5",
				"AU", "B7", "DU", "GC", "GD", "GP", "IF", "UI", "GI", "IA", "IB", "VG", "VI", "VJ", "VK", "5D", "FL",
				"HQ", "M3", "MK", "MQ", "R4", "WS", "6A", "19", "2D", "2P", "3Y", "4L", "4M", "4V", "4X", "5T", "64",
				"65", "6Q", "8A", "91", "9C", "C5", "E0", "E4", "E5", "ED", "EE", "EO", "F2", "F4", "F7", "F8", "FO",
				"FW", "G0", "G8", "G9", "GJ", "GK", "GL", "GM", "GN", "GO", "GQ", "GR", "GS", "GT", "GW", "GX", "GY",
				"GZ", "HF", "I2", "IG", "IM", "K4", "K6", "K7", "K8", "MA", "MC", "PQ", "R6", "UD", "HF", "IA", "IB",
				"ID", "IE", "IF", "IG", "IT", "IS", "IU", "IR", "IV", "IY", "IZ", "IW", "IX", "JZ" };

		test = new String[] { "16", "21", "52", "1M", "2C", "2G", "2H", "5U", "5X", "67", "6J", "6U", "6V", "7F", "7S",
				"8J", "8N", "8W", "9F", "9G", "9H", "9J", "9R", "9S", "9T", "AN", "BO", "BQ", "CY", "DG", "DY", "EZ",
				"FB", "FD", "FF", "FG", "FH", "G7", "GA", "GB", "GG", "GH", "GU", "GV", "I0", "I1", "KV", "MF", "MG",
				"MN", "MP", "R7", "TA", "TB", "US", "UT", "UV", "I0", "I1", "IL", "IK", "IQ"

		};

		test = new String[] { "6A", "19", "2D", "2P", "3Y", "4L", "4M", "4V", "4X", "5T", "64", "65", "6Q", "8A", "91",
				"9C", "C5", "E0", "E4", "E5", "ED", "EE", "EO", "F2", "F4", "F7", "F8", "FO", "FW", "G0", "G8", "G9",
				"GJ", "GK", "GL", "GM", "GN", "GO", "GQ", "GR", "GS", "GT", "GW", "GX", "GY", "GZ", "HF", "I2", "IG",
				"IM", "K4", "K6", "K7", "K8", "MA", "MC", "PQ", "R6", "UD", "IT", "IS", "IU" };

		List<String> list = Arrays.asList(test);
		System.out.println(list.size());
		System.out.println(list);
		TreeSet<String> set = new TreeSet<>(list);
		System.out.println(set);
		System.out.println(set.size());
	}

}
