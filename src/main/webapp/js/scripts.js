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
    var edu_form_next = 0;
    $("#edu-form-add-more").click(function(e){
        e.preventDefault();
        var addto = "#field" + edu_form_next;
        var addRemove = "#field" + (edu_form_next);
        edu_form_next = edu_form_next + 1;
        var newIn = '<div id="field'+ edu_form_next +'" class="p-t-25"><!-- Text input--><div><label class="col-md-2 col-md-offset-2 control-label" for="action_id">ชื่อสถานศึกษา :</label><div class="col-md-8"><input id="school-name" name="school-name" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><br><br><!-- Text input--><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">สาขาวิชา :</label><div class="col-md-8"><input id="discipline" name="discipline" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><br><br><!-- Text input--><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">เริ่มปี พ.ศ. :</label><div class="col-md-8"><input id="eff-academic-year" name="eff-academic-year" type="text" placeholder="25xx" class="form-control input-md w-75-px"></div></div><br><br><!-- Text input--><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">สำเร็จปี พ.ศ. :</label><div class="col-md-8"><input id="" name="end-academic-year" type="end-academic-year" placeholder="25xx" class="form-control input-md w-75-px"></div></div><br><br></div>';
        var newInput = $(newIn);
        var removeBtn = '<div class="row"><div class="col-md-9 col-offset-md-3 text-right"><button id="remove' + (edu_form_next - 1) + '" class="btn btn-danger edu-remove" >Remove</button></div></div>';
        var removeButton = $(removeBtn);
        $(addto).after(newInput);
        $(addRemove).after(removeButton);
        $("#field" + edu_form_next).attr('data-source',$(addto).attr('data-source'));
        $("#count").val(edu_form_next);
        $('.edu-remove').click(function(e){
            e.preventDefault();
            var fieldNum = this.id.charAt(this.id.length-1);
            var fieldID = "#field" + fieldNum;
            console.log(fieldID);
            $(this).remove();
            $(fieldID).remove();
        });
    });

    var skill_form_next = 0;
    $("#skill-form-add-more").click(function(e){
        e.preventDefault();
        var addto = "#skill-field" + skill_form_next;
        var addRemove = "#skill-field" + (skill_form_next);
        skill_form_next = skill_form_next + 1;
        var newIn = '<div id="skill-field'+ skill_form_next +'" class="p-t-25"><!-- Text input--><div class="row m-0"><label class="text-right col-md-2 col-md-offset-2 control-label" for="action_id">ภาษา :</label><div class="col-md-8"><input id="school-name" name="school-name" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><!-- Text input--><div class="row m-0"><label class="text-right col-md-2 col-md-offset-2 control-label" for="discipline">พูด :</label><div class="col-md-8"><div class="row"><div class="col-md-3"><input id="langSpeakingLv" name="langSpeakingLv" type="radio" placeholder="" class="input-md ">ดี&nbsp;&nbsp;<input id="langSpeakingLv" name="langSpeakingLv" type="radio" placeholder="" class="input-md ">พอใช้&nbsp;&nbsp;<input id="langSpeakingLv" name="langSpeakingLv" type="radio" placeholder="" class="input-md ">น้อย</div><div class="col-md-9"></div></div></div></div><!-- Text input--><div class="row m-0"><label class="text-right col-md-2 col-md-offset-2 control-label" for="discipline">อ่าน :</label><div class="col-md-8"><div class="row"><div class="col-md-3"><input id="langReadingLv" name="langReadingLv" type="radio" placeholder="" class="input-md ">ดี&nbsp;&nbsp;<input id="langReadingLv" name="langReadingLv" type="radio" placeholder="" class="input-md ">พอใช้&nbsp;&nbsp;<input id="langReadingLv" name="langReadingLv" type="radio" placeholder="" class="input-md ">น้อย</div><div class="col-md-9"></div></div></div></div><!-- Text input--><div class="row m-0"><label class="text-right col-md-2 col-md-offset-2 control-label" for="discipline">เขียน</label><div class="col-md-8"><div class="row"><div class="col-md-3"><input id="langWrittingLv" name="langWrittingLv" type="radio" placeholder="" class="input-md ">ดี&nbsp;&nbsp;<input id="langWrittingLv" name="langWrittingLv" type="radio" placeholder="" class="input-md ">พอใช้&nbsp;&nbsp;<input id="langWrittingLv" name="langWrittingLv" type="radio" placeholder="" class="input-md ">น้อย</div><div class="col-md-9"></div></div></div></div></div>';
        var newInput = $(newIn);
        var removeBtn = '<div class="row"><div class="col-md-9 col-offset-md-3 text-right"><button id="remove' + (skill_form_next - 1) + '" class="btn btn-danger skill-remove" >Remove</button></div></div>';
        var removeButton = $(removeBtn);
        $(addto).after(newInput);
        $(addRemove).after(removeButton);
        $("#skill-field" + skill_form_next).attr('data-source',$(addto).attr('data-source'));
        $("#count").val(skill_form_next);
        $('.skill-remove').click(function(e){
            e.preventDefault();
            var fieldNum = this.id.charAt(this.id.length-1);
            var fieldID = "#skill-field" + fieldNum;
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
