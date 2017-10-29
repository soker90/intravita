							 </div><!--/row-->  
								<div class="row" id="footer">    
								  <div class="col-sm-6">
									
								  </div>
								  <div class="col-sm-6">
									<p>
									<a href="#" class="pull-right">©Mens Ubiqua 2017</a>
									</p>
								  </div>
								</div>
							  
							  <hr>
								
							  <hr>
								
							  
							</div><!-- /col-9 -->
						</div><!-- /padding -->
					</div>
					<!-- /main -->
				  
				</div>
			</div>
		</div>


		<!--post modal-->
		<div id="postModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
		  <div class="modal-dialog">
			  <div class="modal-content">
				  <div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						Nueva publicación
				  </div>
				  <form class="form center-block" action="/intravita/user/publicar" method="post">
					  <div class="modal-body">
						  
						<div class="form-group">
						  <textarea id="texto" name="texto" class="form-control input-lg" autofocus="" placeholder="Escribe aqui tu publicación"></textarea>
						</div>
						  
					  </div>
					  <div class="modal-footer">
				        <div class="col-xs-5 selectContainer">
				            <select class="form-control" name="privacidad" id="texto">
				                <option value="publica">Publica</option>
				                <option value="privada">Privada</option>
				            </select>
				        </div>
						<button type="submit" name="submit" value="submit" class="btn btn-primary btn-sm" data-dismiss="modal" aria-hidden="true">Aceptar</button>
					  </div>
				  </form>	
				</div>
		  </div>
		</div>
        <script type="text/javascript" src="/intravita/resources/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="/intravita/resources/js/bootstrap.js"></script>
        
        <script type="text/javascript">
        $(document).ready(function() {
			$('[data-toggle=offcanvas]').click(function() {
				$(this).toggleClass('visible-xs text-center');
				$(this).find('i').toggleClass('glyphicon-chevron-right glyphicon-chevron-left');
				$('.row-offcanvas').toggleClass('active');
				$('#lg-menu').toggleClass('hidden-xs').toggleClass('visible-xs');
				$('#xs-menu').toggleClass('visible-xs').toggleClass('hidden-xs');
				$('#btnShow').toggle();
			});
        });
        </script>
        
        <script type="text/javascript" src="/intravita/resources/js/jquery.dataTables.min.js"></script>
 		<script type="text/javascript" src="/intravita/resources/js/dataTables.bootstrap.min.js"></script>
  
	 <script>
	 	$(document).ready(function() {
		    $('#usuarios').DataTable();
		} );
	 </script>
</body></html>