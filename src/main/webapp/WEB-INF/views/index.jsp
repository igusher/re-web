<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp"%>


	<c:out value="${previousResult}"></c:out>
<form id="submitQuery" name='submitQuery'
	action='/motm/motm/re/index' method='POST'>
	<table>
		<tr>
			<td style="text-align: right; padding: 2px;">Merid:</td>
			<td style="padding: 2px;">
				<input id="merid" type='text' name='merid' value=''>
			</td>
		</tr>
		<tr>
			<td style="text-align: right; padding: 2px;">Insees:</td>
			<td style="padding: 2px;">
				<input id="insees" type='text' name='insees' value=''>
			</td>
		</tr>
		<tr>
			<td style="text-align: right; padding: 2px;">AgeGroup:</td>
			<td style="padding: 2px;">
				<input id="ageGroup" type='text' name='ageGroup' value=''>
			</td>
		</tr>
		<tr>
			<td style="text-align: right; padding: 2px;">Gender:</td>
			<td style="padding: 2px;">
				<input id="gender" type='text' name='gender' value=''>
			</td>
		</tr>
		<tr>
			<td style="text-align: right; padding: 2px;">From Date:</td>
			<td style="padding: 2px;">
				<input id="fromDate" type='text' name='fromDate' value=''>
			</td>
		</tr>
		<tr>
			<td style="text-align: right; padding: 2px;">To Date:</td>
			<td style="padding: 2px;">
				<input id="toDate" type='text' name='toDate' value=''>
			</td>
		</tr>
		<tr>
			<td style="text-align: right; padding: 2px;">MinTrxNum:</td>
			<td style="padding: 2px;">
				<input id="minTrxNum" type='text' name='minTrxNum' value=''>
			</td>
		</tr>
		<tr>
			<td style="text-align: right; padding: 2px;">MaxTrxNum:</td>
			<td style="padding: 2px;">
				<input id="maxTrxNum" type='text' name='maxTrxNum' value=''>
			</td>
		</tr>
	</table>
	<input type="submit">
</form>

</section name="content">
</body>

<!--script type="text/javascript">
	$(function() {
		$("#dialogCreatePlayer").dialog({
			modal : true,
			resizable : false,
			draggable : false,
			show: "fade",
			width : 350,
			resizable : false,
			closeOnEscape : false,
			closeText : 'hide',
		

			buttons : [ {
				text : "Create",
				width : "100px",
				click : function() {
					$("#createPlayerForm").submit();
					$(this).dialog("close");
					//window.location = "/ssdp-server/RestManager/web/signup/signup";
				}	
				
			}]
		});
		$("#dialogCreatePlayer").dialog('close');
	});
	
</script-->

