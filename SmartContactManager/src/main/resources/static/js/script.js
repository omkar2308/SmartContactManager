console.log("this is script file")

const toggleSidebar = () => {

	if($(".sidebar").is(":visible")) {
		//true
		//band krna he 
		$(".sidebar").css("display", "none");
		$(".content").css("margin-left", "0%");

	} else{
		//false
		//show krna he 
		$(".sidebar").css("display", "block");
		$(".content").css("margin-left", "20%");
	}
};