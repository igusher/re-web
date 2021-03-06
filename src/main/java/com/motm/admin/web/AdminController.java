package com.motm.admin.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.ILogic;
import logic.Logic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import data.AgeGroup;
import data.Gender;
import data.REQuery;

@Controller
@RequestMapping("/re")
public class AdminController
{
	Logger logger = Logger.getLogger("RedemptionEngine");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	ILogic logic = new Logic();
	
	public AdminController()
	{
		try
		{
			logger.addHandler(new FileHandler());
		} catch (SecurityException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@RequestMapping(value="/init",method=RequestMethod.GET)
	@ResponseBody
	public String init()
	{
		try{
			logic.setUp();
		}
		catch(Exception e)
		{
			logic =null;
			e.printStackTrace();
			return "no good";
		}
		return "good";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public ModelAndView submitQuery(
			@RequestParam(value="merid") String merid,
			@RequestParam(value="insees") String insees,
			@RequestParam(value="ageGroup") int ageGroup,
			@RequestParam(value="gender") String gender,
			@RequestParam(value="fromDate") String fromDate,
			@RequestParam(value="toDate") String toDate,
			@RequestParam(value="minTrxNum") int minTrxNum,
			@RequestParam(value="maxTrxNum") int maxTrxNum
			)
	{
		logger.log(Level.INFO, "start method 'submit query'");
		System.out.println("start method 'submit query'");
		System.out.println("merid: " + merid);
		System.out.println("insees: " + insees);
		System.out.println("ageGroup: " + ageGroup);
		System.out.println("gender: " + gender);
		System.out.println("fromDate: " + fromDate);
		System.out.println("toDate: " + toDate);
		System.out.println("minTrxsNum: " + minTrxNum);
		System.out.println("maxTrxsNum: " + maxTrxNum);
		
		ModelAndView view = new ModelAndView("/WEB-INF/views/index.jsp");
		if(logic == null)
		{
			logger.log(Level.INFO, "logic == null");
			System.out.println("logic == null");
			view.addObject("isError", true);
			view.addObject("errorMessage", "Redemption Engine is not properly initialized");	
			return view;
		}
		
		AgeGroup ageGroupObj = AgeGroup.parse(ageGroup);
		Gender genderObj = Gender.parse(gender);
		
		Date fromDateObj = null;
		Date toDateObj = null;
		try
		{
			fromDateObj = sdf.parse(fromDate);
			toDateObj = sdf.parse(toDate);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException("Invalid fromDate or toDate parameter. Use fromat 'yyyy-MM-dd'.", e);
		}
		
		REQuery reQuery = new REQuery(genderObj, ageGroupObj, Arrays.asList(insees.split(" ")), minTrxNum, maxTrxNum, fromDateObj, toDateObj, merid, null);
		int acidsCount = logic.getAcidsNum(reQuery);
		view.addObject("previousResult", acidsCount);
		logger.log(Level.INFO, "normal finish method submitQuery. return " + acidsCount);
		System.out.println("normal finish method submitQuery. return " + acidsCount);
		return view;
		
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView getIndex()
	{
		logger.log(Level.INFO, "start method getIndex");
		System.out.println("start method getIndex");
		ModelAndView view = new ModelAndView("/WEB-INF/views/index.jsp");
		if(logic == null)
		{
			view.addObject("isError", true);
			view.addObject("errorMessage", "Redemption Engine is not properly initialized");
			logger.log(Level.INFO, "logic == null");
			System.out.println("logic == null");
		}	
		logger.log(Level.INFO, "normal finish method getIndex");
		System.out.println("normal finish method getIndex");
		return view;
	}
	
	@RequestMapping(value="/submitTrxs", method = RequestMethod.POST, headers="content-type=text/plain")
	@ResponseBody
	public String submitTrxs(@RequestBody String bunchOfTrxs)
	{
		//System.out.println("start method submitTrxs");
//		System.out.println("input data are: ");
//		System.out.println(bunchOfTrxs);
		return ""+ logic.submitTrxsAsTextBlock(bunchOfTrxs);
	}
}

