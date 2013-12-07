<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>IdeaBroker</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/STYLEDOCARALHOPARAIDEIAS.css" rel="stylesheet">
    <link rel="stylesheet" href="Notifications/notifications.css">

</head>
<body>
<s:if test="%{SetResult=='1'}">
    <script>
        window.location.replace("/ViewIdea.action?idea=<s:property value="IdeaTitle"/>&SetResult=3");
    </script>
</s:if>
<s:if test="%{SetResult=='2'}">
    <script>
        window.location.replace("/ViewIdea.action?idea=<s:property value="IdeaTitle"/>&SetResult=4");
    </script>
</s:if>
<s:if test="%{DeleteResult=='1'}">
    <script>
        window.location.replace("/ViewIdea.action?idea=<s:property value="IdeaTitle"/>&DeleteResult=3");
    </script>
</s:if>
<s:if test="%{DeleteResult=='2'}">
    <script>
        window.location.replace("/ViewIdea.action?idea=<s:property value="IdeaTitle"/>&DeleteResult=4");
    </script>
</s:if>
<s:if test="%{BuyResult=='1'}">
    <script>
        window.location.replace("/ViewIdea.action?idea=<s:property value="IdeaTitle"/>&BuyResult=4");
    </script>
</s:if>
<s:if test="%{BuyResult=='2'}">
    <script>
        window.location.replace("/ViewIdea.action?idea=<s:property value="IdeaTitle"/>&BuyResult=5");
    </script>
</s:if>
<s:if test="%{BuyResult=='3'}">
    <script>
        window.location.replace("/ViewIdea.action?idea=<s:property value="IdeaTitle"/>&BuyResult=6");
    </script>
</s:if>
<s:if test="%{watchListResult=='1'}">
    <script>
        window.location.replace("/ViewIdea.action?idea=<s:property value="IdeaTitle"/>&watchListResult=4");
    </script>
</s:if>
<s:if test="%{watchListResult=='2'}">
    <script>
        window.location.replace("/ViewIdea.action?idea=<s:property value="IdeaTitle"/>&watchListResult=5");
    </script>
</s:if>
<s:if test="%{watchListResult=='3'}">
    <script>
        window.location.replace("/ViewIdea.action?idea=<s:property value="IdeaTitle"/>&watchListResult=6");
    </script>
</s:if>
<style>
    body { background: url(img/background.png); }
</style>

<%@include file="header.jsp" %>

<div class="container">
    <s:if test="%{SetResult=='3'}">
        <div class="isa_error">Error setting the selling price. Try Again.</div>
    </s:if>
    <s:if test="%{SetResult=='4'}">
        <div class="isa_success">The selling price has been changed with success!</div>
    </s:if>
    <s:if test="%{DeleteResult=='3'}">
        <div class="isa_error">Error deleting the idea. Try again.</div>
    </s:if>
    <s:if test="%{BuyResult=='4'}">
        <div class="isa_error">Error buying the idea. Try again.</div>
    </s:if>
    <s:if test="%{BuyResult=='6'}">
        <div class="isa_success">The idea has been bought with success!</div>
    </s:if>
    <s:if test="%{BuyResult=='5'}">
        <div class="isa_info">The buy request has been added to the queue!</div>
    </s:if>
    <s:if test="%{watchListResult=='4'}">
        <div class="isa_error">Error modifying the WatchList the idea. Try again.!</div>
    </s:if>
    <s:if test="%{watchListResult=='5'}">
        <div class="isa_success">The idea has been removed from the WatchList!</div>
    </s:if>
    <s:if test="%{watchListResult=='6'}">
        <div class="isa_success">The idea has been added to the WatchList!</div>
    </s:if>
        <h1>Details of the idea '<s:property value="ideaDet.title"/>':</h1>
        <p></p>

    <hr>
     <s:if test="%{DeleteResult=='4'}">
        <div class="isa_success">The idea has been deleted with success!</div>
     </s:if>
     <s:else>

        <div class="jumbotron">
        <!-- Modal Delete -->
        <div class="modal fade" id="Delete<s:property value="#idea.ideaID"/>" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Delete Idea <s:property value="#ideaDet.title"/></h4>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete?
                    </div>
                    <form method="post" action = "DeleteIdea" class="modal-footer">
                        <input type="text" style="display: none" name = "IdeaTitle" value="<s:property value="#ideaDet.title"/>">
                        <button type="submit" class="btn btn-default">Yes</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                    </form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <!-- Modal Set Share -->
        <div class="modal fade" id="setShares<s:property value="#idea.ideaID"/>" tabindex="-1" role="dialog" aria-labelledby="setSharesModalLabel" aria-hidden="true">
            <form method="post" action="SetSharePrice" class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="newPriceLabel">Set new price (per Share)</h4>
                    </div>
                    <div class="modal-body">
                        <div>
                            <fieldset>
                                <!-- Buy Price-->
                                <div class="control-group">
                                    <label class="control-label" for="textinput">Selling price (per Share)</label>
                                    <div class="controls">
                                        <input id="textinput" name="SharesPrice" type="text" placeholder="" class="input-mini">

                                    </div>
                                </div>
                            </fieldset>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <input type="text" style="display: none" name = "IdeaTitle" value="<s:property value="ideaDet.title"/>">
                        <button type="submit" class="btn btn-default" >Yes</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                    </div>
                </div><!-- /.modal-content -->
            </form><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <!-- Modal Buy Shares-->
        <div class="modal fade" id="BuyIdeas<s:property value="#idea.ideaID"/>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <form method="post" action="BuyIdea" class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="buySharesLabel">Buy shares</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-horizontal">
                            <fieldset>

                                <!-- Selling Price-->
                                <div class="control-group">
                                    <label class="control-label" for="sellingPriceFieldt">Selling Price (per Share)</label>
                                    <div class="controls">
                                        <input id="sellingPriceFieldt" name="SellingPrice" type="text" placeholder="" class="input-mini">

                                    </div>
                                </div>

                                <!-- Buy Price-->
                                <div class="control-group">
                                    <label class="control-label" for="buyPriceField">Buy price (per Share)</label>
                                    <div class="controls">
                                        <input id="buyPriceField" name="buyPrice" type="text" placeholder="" class="input-mini">

                                    </div>
                                </div>

                                <!-- Number of Shares-->
                                <div class="control-group">
                                    <label class="control-label" for="numberSharesField">Number of Shares</label>
                                    <div class="controls">
                                        <input id="numberSharesField" name="numberShares" type="text" placeholder="" class="input-mini">

                                    </div>
                                </div>

                            </fieldset>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="text" style="display: none" name = "IdeaTitle" value="<s:property value="ideaDet.title"/>">
                        <button type="submit" class="btn btn-default" >Buy Shares</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                    </div>
                </form><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <p></p>
        <div class="media">
            <div class="media-body">
                <h3 class="media-heading"><s:property value="ideaDet.title"/></h3>
                <p><s:property value="ideaDet.description"/></p>
                <p></p>
                <p><h5 class="media-heading">Idea Market Price:</h5> <s:property value="ideaDet.ideaValueString"/></p>
                <p><h5 class="media-heading">Idea Original Price:</h5> <s:property value="ideaDet.initValueString"/></p>
                <p><h5 class="media-heading">Creation date:</h5> <s:property value="ideaDet.date"/></p>
                <p><h5 class="media-heading">Owners of the idea:</h5>
                <s:iterator var="owners" value="ideaDet.owners">
                    <s:property value="#owners"/> &nbsp
                </s:iterator>
                </p>
                <s:iterator var="topic" value="ideaDet.topics">
                    <a href="ViewTopic.action?topic=<s:property value="#topic"/>"><span class="label label-info">#<s:property value="#topic"/></span></a>
                </s:iterator>
                <!--<span class="label label-primary">caralho</span>
                <span class="label label-success">SD</span>
                <span class="label label-info">CACEIRO</span>
                <span class="label label-warning">CARDOUZO</span>
                <span class="label label-danger">TOPICSLOLOL</span> -->
                <p></p>

                <div class="btn-group">
                    <s:if test="%{isRoot==false}">
                            <div class="isa_success">The idea has been deleted with success!</div>

                        <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#BuyIdeas<s:property value="#idea.ideaID"/>">
                            <span class="glyphicon glyphicon-euro"></span> Buy Shares
                        </button>
                        <span></span>
                        <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#setShares<s:property value="#idea.ideaID"/>">
                            <span class="glyphicon glyphicon-cog"></span> Set Shares
                        </button>
                        <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#Delete<s:property value="#idea.ideaID"/>">
                            <span class="glyphicon glyphicon-trash"></span> Delete
                        </button>
                        <s:if test="%{inWatchList==0}">
                        <a href="Watchlist.action?idea=<s:property value="#idea.title"/>">
                            <button class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-star"></span> Add to WatchList
                            </button>
                        </a>
                        </s:if>
                        <s:else>
                            <a href="Watchlist.action?idea=<s:property value="#idea.title"/>">
                                <button class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-star"></span> Remove from WatchList
                                </button>
                            </a>
                        </s:else>
                    </s:if>
                    <s:else>
                        <a href="Takeover.action?idea=<s:property value="#idea.title"/>">
                            <button class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-star"></span> Takeover
                            </button>
                        </a>
                    </s:else>
                </div>
            </div>
        </div>
     </s:else>
</div>





    <div id="push"></div>

<div id="footer">
    <div class="container">
        <p>Powered by: <a href="https://www.facebook.com/bruno.caceiro">Bruno Caceiro</a> & <a href="https://www.facebook.com/david.p.cardoso">David Cardoso</a>.</p>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>