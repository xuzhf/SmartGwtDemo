<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	HttpSession sessions=request.getSession();
	String memberId = null;
	String loginName = "";
	if(sessions.getAttribute("memberId") != null){
		memberId = sessions.getAttribute("memberId").toString();
		loginName = sessions.getAttribute("loginName").toString();
	}
	if(memberId ==null || memberId.equals("")){
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta name="gwt:property" content="locale=zh_CN">
<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1, width=device-width, user-scalable=no, minimum-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-capable" content="yes">
	
<head>
    <title>系统</title>
    <!--CSS for loading message at application Startup-->
    <!--CSS for loading message at application Startup-->
    <style type="text/css">
        html, body { overflow:hidden }
        #loadingWrapper {
            position: absolute;
            top: 40%;
            width: 100%;
            text-align: center;
            z-index: 900001;
        }
        #loading {
            margin: 0 auto;
            border: 1px solid #ccc;
            width: 160px;
            padding: 2px;
            text-align: left;
        }

        #loading a {
            color: #225588;
        }

        #loading .loadingIndicator {
            background: white;
            font: bold 13px tahoma, arial, helvetica;
            padding: 10px;
            margin: 0;
            height: auto;
            color: #444;
        }

        #loadingMsg {
            font: normal 10px arial, tahoma, sans-serif;
        }
    </style>
    <link rel="stylesheet" href="Showcase.css?isc_version=10.0">
    <link rel="stylesheet" href="css/CssStylesSample.css?isc_version=10.0">
</head>
<body>
<iframe id="__gwt_historyFrame" style="width:0;height:0;border:0"></iframe>

<!--add loading indicator while the app is being loaded-->
<div id = "memberId"  style="display: none"><%=memberId%></div>
<div id = "loginName"  style="display: none"><%=loginName%></div>
<div id="loadingWrapper">
<div id="loading">
    <div class="loadingIndicator">
        <!--<img src="images/pieces/48/cube_green.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/>SmartGWT<br/>-->
        <span id="loadingMsg">Loading styles and images...</span></div>
</div>
</div>


<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = '正在加载 Core API...';</script>

<!--include the SC Core API-->
<script src='showcase/sc/modules/ISC_Core.js'></script>
<script> var isomorphicDir = "../sc/"; </script>
<!--include SmartClient -->
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = '正在加载 UI 组件...';</script>
<script src='showcase/sc/modules/ISC_Foundation.js'></script>
<script src='showcase/sc/modules/ISC_Containers.js'></script>
<script src='showcase/sc/modules/ISC_Grids.js'></script>
<script src='showcase/sc/modules/ISC_Forms.js'></script>
<script src='showcase/sc/modules/ISC_RichTextEditor.js'></script>
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = '正在加载 Data API...';</script>
<script src='showcase/sc/modules/ISC_DataBinding.js'></script>
<script src='showcase/sc/modules/ISC_Calendar.js'></script>
<script src='showcase/sc/modules/ISC_Drawing.js'></script>

<script>
function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

// Determine what skin file to load
var currentSkin = readCookie('skin_name_2_4');
if (currentSkin == null) currentSkin = "Enterprise";
</script>

<!--load skin-->
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = '正在加载外观 ...';</script>

<script type="text/javascript">
document.write("<"+"script src=showcase/sc/skins/" + currentSkin + "/load_skin.js?isc_version=10.0.js><"+"/script>");
</script>

<!--include the application JS-->
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = '正在加载 ... 请稍等...';</script>
<script type="text/javascript" src="showcase/showcase.nocache.js"></script>


</body>
</html>
