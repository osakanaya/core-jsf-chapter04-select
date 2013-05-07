package com.corejsf;

import java.awt.Color;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named("form")
@SessionScoped
public class RegisterForm implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Education {
		HIGH_SCHOOL, BACHELOR, MASTER, DOCTOR
	};

	public static class Weekday {
		private int dayOfWeek;

		public Weekday(int dayOfWeek) {
			this.dayOfWeek = dayOfWeek;
		}

		public String getDayName() {
			DateFormatSymbols symbols = new DateFormatSymbols();
			String[] weekdays = symbols.getWeekdays();

			return weekdays[dayOfWeek];
		}

		public int getDayNumber() {
			return dayOfWeek;
		}
	}

	private String name;
	private boolean contactMe;
	private int[] bestDaysToContact;
	private Integer yearOfBirth;
	private int[] colors;
	private Set<String> languages = new TreeSet<String>();
	private Education education = Education.BACHELOR;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isContactMe() {
		return contactMe;
	}

	public void setContactMe(boolean contactMe) {
		this.contactMe = contactMe;
	}

	public int[] getBestDaysToContact() {
		return bestDaysToContact;
	}

	public void setBestDaysToContact(int[] bestDaysToContact) {
		this.bestDaysToContact = bestDaysToContact;
	}

	public int[] getColors() {
		return colors;
	}

	public void setColors(int[] colors) {
		this.colors = colors;
	}

	public Set<String> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<String> languages) {
		this.languages = languages;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public Integer getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(Integer yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public Collection<SelectItem> getYearItems() {
		return birthYears;
	}

	public static Collection<SelectItem> birthYears;
	static {
		birthYears = new ArrayList<SelectItem>();
		birthYears.add(new SelectItem(null, "Pick a year:", "", false, false,
				true));
		for (int i = 1900; i < 2020; i++) {
			birthYears.add(new SelectItem(i));
		}
	};

	public Weekday[] getDaysOfTheWeek() {
		return daysOfTheWeek;
	}

	private static Weekday[] daysOfTheWeek;
	static {
		daysOfTheWeek = new Weekday[7];
		for (int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
			daysOfTheWeek[i - Calendar.SUNDAY] = new Weekday(i);
		}
	};

	public SelectItem[] getLanguageItems() {
		return languageItems;
	}

	private static SelectItem[] languageItems = { 
		new SelectItem("English"),
		new SelectItem("French"), 
		new SelectItem("Russian"),
		new SelectItem("Italian"),
		new SelectItem("Esperanto", "Esperanto", "", true) // disabled
	};

	public SelectItem[] getColorItems() {
		return colorItems;
	}
	
	private static SelectItem[] colorItems = {
		new SelectItem(Color.RED.getRGB(), "Red"),
	      new SelectItem(Color.GREEN.getRGB(), "Green"),
	      new SelectItem(Color.BLUE.getRGB(), "Blue"),
	      new SelectItem(Color.YELLOW.getRGB(), "Yellow"),
	      new SelectItem(Color.ORANGE.getRGB(), "Orange", "", true) // disabled
	};
	
	public Map<String, Education> getEducationItems() {
		return educationItems;
	}
	
	public static Map<String, Education> educationItems;
	static {
		educationItems = new LinkedHashMap<String, Education>();
		educationItems.put("High School", Education.HIGH_SCHOOL);
		educationItems.put("Bachelor's", Education.BACHELOR);
		educationItems.put("Master's", Education.MASTER);
		educationItems.put("Doctorate", Education.DOCTOR);
	};
	
	public String getBestDaysConcatenated() {
		return Arrays.toString(bestDaysToContact);
	}
	
	public String getColorsConcatenated() {
		StringBuilder result = new StringBuilder();
		for(int color : colors) {
			result.append(String.format("%06x ", color));
		}
		
		return result.toString();
	}
}
