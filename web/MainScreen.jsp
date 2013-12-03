<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <title>IdeaBroker</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/STYLEDOCARALHOPARAIDEIAS.css" rel="stylesheet">

</head>
<body>
<style>
    body { background: url(img/background.png); }
</style>

<%@include file="header.jsp" %>

<div class="container">


        <h1>Welcome to IdeaBroker</h1>
        <p>We do amazing things that will make you want to give us money. Lots of
            money.</p>
        <p></p>


    <hr>

    <!-- Modal Buy Shares-->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="buySharesLabel">Buy shares</h4>
                </div>
                <div class="modal-body">

                    <form class="form-horizontal">
                        <fieldset>

                            <!-- Selling Price-->
                            <div class="control-group">
                                <label class="control-label" for="textinput">Selling Price (per Share)</label>
                                <div class="controls">
                                    <input id="sellingPriceFieldt" name="textinput" type="text" placeholder="" class="input-mini">

                                </div>
                            </div>

                            <!-- Buy Price-->
                            <div class="control-group">
                                <label class="control-label" for="textinput">Buy price (per Share)</label>
                                <div class="controls">
                                    <input id="buyPriceField" name="textinput" type="text" placeholder="" class="input-mini">

                                </div>
                            </div>

                            <!-- Number of Shares-->
                            <div class="control-group">
                                <label class="control-label" for="textinput">Number of Shares</label>
                                <div class="controls">
                                    <input id="numberSharesField" name="numberShares" type="text" placeholder="" class="input-mini">

                                </div>
                            </div>

                        </fieldset>
                    </form>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Buy Shares</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->



    <!-- Modal Delete -->
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="deleteIdeaLabel">Delete Idea :nome Idea:</h4>
                </div>
                <div class="modal-body">
                    Are you sure you want to delete?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Yes</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


    <!-- Modal Set Share -->
    <div class="modal fade" id="setSharesModal" tabindex="-1" role="dialog" aria-labelledby="setSharesModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="newPriceLabel">Set new price (per Share)</h4>
                </div>
                <div class="modal-body">

                    <form>
                        <fieldset>
                            <!-- Buy Price-->
                            <div class="control-group">
                                <label class="control-label" for="textinput">Selling price (per Share)</label>
                                <div class="controls">
                                    <input id="textinput" name="textinput" type="text" placeholder="" class="input-mini">

                                </div>
                            </div>
                        </fieldset>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Yes</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->



    <div class="jumbotron">
        <p></p>
        <div class="media">
            <!--
                Caso queiramos ter uma imagem!
              <a class="pull-left" href="#">    <img class="media-object" src="https://app.divshot.com/img/placeholder-64x64.gif">  </a>-->

            <div class="media-body">
                <h4 class="media-heading">Title goes here</h4>
                <p>Description goes here, blaaskdjnaskjhdbnasjhdbasjdbasjhdbasjhdbasjhdbasjhdbasjdhasbdjhasbdasjhdbasjhdbasjhdasbdjhasbajhdbajhsdbajshdbasjhdbasjhdbasjhdbasjdhbasjhdbasjhdbasjhdbasjhdbasjhdb</p>
                <p></p>
                <span class="label label-default">merda</span>
                <span class="label label-primary">caralho</span>
                <span class="label label-success">SD</span>
                <span class="label label-info">CACEIRO</span>
                <span class="label label-warning">CARDOUZO</span>
                <span class="label label-danger">TOPICSLOLOL</span>
                <p></p>

                <div class="btn-group">
                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">
                        <span class="glyphicon glyphicon-euro"></span> Buy Shares
                    </button>
                    <span></span>
                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#setSharesModal">
                        <span class="glyphicon glyphicon-cog"></span> Set Shares
                    </button>
                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#deleteModal">
                        <span class="glyphicon glyphicon-trash"></span> Delete
                    </button>
                </div>
            </div>
        </div>
    </div>



    <div class="jumbotron">
        <p></p>
        <div class="media">
            <!--
              Caso queiramos ter uma imagem!
              <a class="pull-left" href="#">    <img class="media-object" src="https://app.divshot.com/img/placeholder-64x64.gif">  </a>-->

            <div class="media-body">
                <h4 class="media-heading">Title goes here</h4>
                <p>Description goes here, blaaskdjnaskjhdbnasjhdbasjdbasjhdbasjhdbasjhdbasjhdbasjdhasbdjhasbdasjhdbasjhdbasjhdasbdjhasbajhdbajhsdbajshdbasjhdbasjhdbasjhdbasjdhbasjhdbasjhdbasjhdbasjhdbasjhdb</p>
                <p></p>
                <span class="label label-default">merda</span>
                <span class="label label-primary">caralho</span>
                <span class="label label-success">SD</span>
                <span class="label label-info">CACEIRO</span>
                <span class="label label-warning">CARDOUZO</span>
                <span class="label label-danger">TOPICSLOLOL</span>
                <p></p>

                <div class="btn-group">
                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">
                        <span class="glyphicon glyphicon-euro"></span> Buy Shares
                    </button>
                    <span></span>
                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#setSharesModal">
                        <span class="glyphicon glyphicon-cog"></span> Set Shares
                    </button>
                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#deleteModal">
                        <span class="glyphicon glyphicon-trash"></span> Delete
                    </button>
                </div>
            </div>
        </div>
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