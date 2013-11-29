<%--
     <%@ taglib prefix="c" uri="sta" %>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>IdeaBroker</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/submitStyle.css" rel="stylesheet">
    <link rel="stylesheet" href="Notifications/notifications.css">

</head>
<body>

<style>
    body { background: url(img/background.png); }
</style>

<%@include file="header.jsp" %>

<!-- Main container -->
<form method="post" action="SubmitIdea" class="" style="padding-left: 100px; padding-right: 100px;" enctype="multipart/form-data">

    <!-- Submit new idea panel -->
    <div class="panel panel-default"  >

        <div class="panel-heading">
            <h3 class="panel-title">
                <i class="fa fa-flash" style="margin-right: 3px;"></i>
                What's your idea?
            </h3>
        </div>

        <p></p>

        <div class="panel-body">
            <s:if test="%{tried==true}">

            </s:if>
            <s:if test="%{submitted==true}">
                <div class="isa_success">Idea added with success!.</div>
            </s:if>
            <s:if test="%{error==true}">
                <div class="isa_error"><s:property value="errorMessage"/></div>
            </s:if>
            <div class="form-group">
                <label for="titleIdea">Title</label>
                <input type="text" name = "newIdea.titleIdea" type="title" class="form-control-title" id="titleIdea" placeholder="Enter title of idea"/>
            </div>

            <label for = "descriptionIdea" > Description </label>
            <textarea name = "newIdea.descriptionIdea" id = "descriptionIdea" class="form-control" rows="3" style = "resize: none;"></textarea>

            <p></p>
            <div class="col-lg-6" >
                <div class="input-group">
                    <input type="text" name = "newTopic" id = "newTopic"type="text" class="form-control" style="margin-left:0px;"/>
            <span class="input-group-btn">
              <button class="btn btn-default" type="button" onClick="javascript:document.getElementById('topicsList').value+=' #'+document.getElementById('newTopic').value;javascript:document.getElementById('newTopic').value='';">Add Topic</button>
            </span>
                </div><!-- /input-group -->

                <p></p>

                <textarea class="form-control" name = "newIdea.topicsList" rows="3" id = "topicsList" style = "resize: none;" readonly="true"></textarea>

            </div><!-- /.col-lg-6 -->
            <div class="row" style="margin-top: 20px;">
                <div class="col-md-2">
                    <div class="form-group">
                        <label class="control-label" for="deiCoinsIdea">Price per Share</label>
                        <input type="text" name = "newIdea.deiCoinsIdea" class="form-control" id="deiCoinsIdea">
                    </div>
                </div>

                <!-- Attach File -->
                <div class="col-md-5">
                    <div class="form-group">
                        <label for="fileUpload">Attach file</label>
                        <s:file name="fileUpload"  size="40" />
                        <p class="help-block">Only images</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <button type="submit" style="float:right;" class="btn btn-default navbar-btn">Submit</button>
</form>
   <p></p>



    <!-- Footer -->
    <div id="push"></div>
    <div id="footer">
        <p class="muted credit">Powered by: <a href="https://www.facebook.com/bruno.caceiro">Bruno Caceiro</a>&
            <a href="https://www.facebook.com/david.p.cardoso">David Cardoso</a>.</p>
    </div>





<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
