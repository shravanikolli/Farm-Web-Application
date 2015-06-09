/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

	
		$(function() {
			// Clickable Dropdown
			$('.click-nav > ul').toggleClass('no-js js');
			$('.click-nav .js ul').hide();
			$('.click-nav .js').click(function(e) {
				$('.click-nav .js ul').slideToggle(200);
				$('.clicker').toggleClass('active');
				e.stopPropagation();
			});
			$(document).click(function() {
				if ($('.click-nav .js ul').is(':visible')) {
					$('.click-nav .js ul', this).slideUp();
					$('.clicker').removeClass('active');
				}
			});
		});

	
		
			var _gaq=[['_setAccount','UA-20440416-10'],['_trackPageview']];
			(function(d,t){var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
			g.src='//www.google-analytics.com/ga.js';
			s.parentNode.insertBefore(g,s)})(document,'script');


                        $(function() {
			// Clickable Dropdown
			$('.click-nav1 > ul').toggleClass('no-js js');
			$('.click-nav1 .js ul').hide();
			$('.click-nav1 .js').click(function(e) {
				$('.click-nav1 .js ul').slideToggle(200);
				$('.clicker1').toggleClass('active');
				e.stopPropagation();
			});
			$(document).click(function() {
				if ($('.click-nav1 .js ul').is(':visible')) {
					$('.click-nav1 .js ul', this).slideUp();
					$('.clicker1').removeClass('active');
				}
			});
		});

{			var _gaq=[['_setAccount','UA-20440416-10'],['_trackPageview']];
			(function(d,t){var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
			g.src='//www.google-analytics.com/ga.js';
			s.parentNode.insertBefore(g,s);})(document,'script');
}
//document.writeln('<div class="click-nav"><ul class="no-js1"><li><a class="clicker"><img src="img/i-3.png" alt="Icon">Account Settings</a><ul><li><a href="facilities.html"><img src="img/i-2.png" alt="Icon">Home</a></li><li><a href="changeExistingPassword.html"><img src="img/i-4.png" alt="Icon">Change Password</a></li><li><a href="login.html"><img src="img/i-6.png" alt="Icon">Sign out</a></li></ul> </li></ul></div> <div class="click-nav1"><ul class="no-js2"><li> <a class="clicker1">Manage Users<img src="img/i-1.png" alt="Icon"></a><ul><li><a href="register.html"><img src="img/i-4.png" alt="Icon">Add User</a></li><li><a href="deleteusers.html"><img src="img/i-4.png" alt="Icon">Delete User</a></li> </ul> </li> </ul> </div> ');