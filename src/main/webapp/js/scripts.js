$(document).ready(function () {
    //Initialize tooltips
    $('.nav-tabs > li a[title]').tooltip();
    
    //Wizard
    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

        var $target = $(e.target);
    
        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    });

    $(".next-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        $active.next().removeClass('disabled');
        nextTab($active);

    });
    $(".prev-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        prevTab($active);

    });
    
    $("#card-id-eff-date, #card-id-end-date, #dob").datepicker({
   	  defaultViewDate: {
   	    year: (new Date().getFullYear() + 543),
   	 	month: (new Date().getMonth()),
     	day: (new Date().getDate())
   	  },
   	  autoclose:true,
   	  format: 'dd/mm/yyyy'
   	});
    
    $('#card-id-eff-date, #card-id-end-date, #dob').datepicker('update', '17/08/2561');

    //@naresh action dynamic childs
    var next = 0;
    $("#add-more").click(function(e){
        e.preventDefault();
        var addto = "#field" + next;
        var addRemove = "#field" + (next);
        next = next + 1;
        var newIn = ' <div id="field'+ next +'" class="p-t-25" name="field'+ next +'"><!-- Text input--><div ><label class="col-md-3 control-label" for="action_id">ชื่อสถานศึกษา</label><div class="col-md-9"><input id="school-name" name="school-name" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><br><br><!-- Text input--><div><label class="col-md-3 control-label" for="discipline">สาขาวิชา</label><div class="col-md-9"><input id="discipline" name="discipline" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><br><br><div><label class="col-md-3 control-label" for="discipline">เริ่มปี พ.ศ.</label><div class="col-md-9"><input id="eff-academic-year" name="eff-academic-year" type="text" placeholder="25xx" class="form-control input-md w-75-px"></div></div><br><br><div><label class="col-md-3 control-label" for="discipline">สำเร็จปี พ.ศ.</label><div class="col-md-9"><input id="" name="end-academic-year" type="end-academic-year" placeholder="25xx" class="form-control input-md w-75-px"></div></div><br><br></div>';
        var newInput = $(newIn);
        var removeBtn = '<button id="remove' + (next - 1) + '" class="btn btn-danger remove-me" >Remove</button>';
        var removeButton = $(removeBtn);
        $(addto).after(newInput);
        $(addRemove).after(removeButton);
        $("#field" + next).attr('data-source',$(addto).attr('data-source'));
        $("#count").val(next);
        $('.remove-me').click(function(e){
            e.preventDefault();
            var fieldNum = this.id.charAt(this.id.length-1);
            var fieldID = "#field" + fieldNum;
            console.log(fieldID);
            $(this).remove();
            $(fieldID).remove();
        });
    });
    
});

function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
}
function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
}

