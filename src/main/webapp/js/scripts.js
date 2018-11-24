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
       
    $("#dateOfStartWork").datepicker({
        startDate: '1d',
        autoclose:true,
        format: 'dd/mm/yyyy'
    });
    
    $('#card-id-eff-date, #card-id-end-date, #dob').datepicker('update', '17/08/2561');

    //@naresh action dynamic childs
    var edu_form_next = 0;
    $("#edu-form-add-more").click(function(e){
        e.preventDefault();
        var addto = "#edu-field" + edu_form_next;
        var addRemove = "#edu-field" + (edu_form_next);
        edu_form_next = edu_form_next + 1;
        var newIn = '<div id="field'+ edu_form_next +'" class="p-t-25"><!-- Text input--><div><label class="col-md-2 col-md-offset-2 control-label" for="action_id">ชื่อสถานศึกษา :</label><div class="col-md-8"><input name="school-name" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><br><br><!-- Text input--><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">สาขาวิชา :</label><div class="col-md-8"><input id="discipline" name="discipline" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><br><br><!-- Text input--><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">เริ่มปี พ.ศ. :</label><div class="col-md-8"><input id="eff-academic-year" name="eff-academic-year" type="text" placeholder="25xx" class="form-control input-md w-75-px"></div></div><br><br><!-- Text input--><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">สำเร็จปี พ.ศ. :</label><div class="col-md-8"><input id="" name="end-academic-year" type="end-academic-year" placeholder="25xx" class="form-control input-md w-75-px"></div></div><br><br></div>';
        var newInput = $(newIn);
        var removeBtn = '<div class="row"><div class="col-md-9 col-offset-md-3 text-right"><button id="edu-remove' + (edu_form_next - 1) + '" class="btn btn-danger edu-remove" >Remove</button></div></div>';
        var removeButton = $(removeBtn);
        $(addto).after(newInput);
        $(addRemove).after(removeButton);
        $("#edu-field" + edu_form_next).attr('data-source',$(addto).attr('data-source'));
        $("#count").val(edu_form_next);
        $('.edu-remove').click(function(e){
            e.preventDefault();
            var fieldNum = this.id.charAt(this.id.length-1);
            var fieldID = "#edu-field" + fieldNum;
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
        var newIn = '<div id="skill-field'+ skill_form_next +'" class="p-t-25"><!-- Text input--><div class="row m-0"><label class="text-right col-md-2 col-md-offset-2 control-label" for="action_id">ภาษา :</label><div class="col-md-8"><input name="skill-name" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><!-- Text input--><div class="row m-0"><label class="text-right col-md-2 col-md-offset-2 control-label" for="discipline">พูด :</label><div class="col-md-8"><div class="row"><div class="col-md-3"><input id="langSpeakingLv" name="langSpeakingLv" type="radio" placeholder="" class="input-md ">ดี&nbsp;&nbsp;<input id="langSpeakingLv" name="langSpeakingLv" type="radio" placeholder="" class="input-md ">พอใช้&nbsp;&nbsp;<input id="langSpeakingLv" name="langSpeakingLv" type="radio" placeholder="" class="input-md ">น้อย</div><div class="col-md-9"></div></div></div></div><!-- Text input--><div class="row m-0"><label class="text-right col-md-2 col-md-offset-2 control-label" for="discipline">อ่าน :</label><div class="col-md-8"><div class="row"><div class="col-md-3"><input id="langReadingLv" name="langReadingLv" type="radio" placeholder="" class="input-md ">ดี&nbsp;&nbsp;<input id="langReadingLv" name="langReadingLv" type="radio" placeholder="" class="input-md ">พอใช้&nbsp;&nbsp;<input id="langReadingLv" name="langReadingLv" type="radio" placeholder="" class="input-md ">น้อย</div><div class="col-md-9"></div></div></div></div><!-- Text input--><div class="row m-0"><label class="text-right col-md-2 col-md-offset-2 control-label" for="discipline">เขียน</label><div class="col-md-8"><div class="row"><div class="col-md-3"><input id="langWrittingLv" name="langWrittingLv" type="radio" placeholder="" class="input-md ">ดี&nbsp;&nbsp;<input id="langWrittingLv" name="langWrittingLv" type="radio" placeholder="" class="input-md ">พอใช้&nbsp;&nbsp;<input id="langWrittingLv" name="langWrittingLv" type="radio" placeholder="" class="input-md ">น้อย</div><div class="col-md-9"></div></div></div></div></div>';
        var newInput = $(newIn);
        var removeBtn = '<div class="row"><div class="col-md-9 col-offset-md-3 text-right"><button id="skill-remove' + (skill_form_next - 1) + '" class="btn btn-danger skill-remove" >Remove</button></div></div>';
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

    var comp_skill_form_next = 0;
    $("#comp-skill-form-add-more").click(function(e){
        console.log("comp-skill-form-add-more");
        e.preventDefault();
        var addto = "#comp-skill-field" + comp_skill_form_next;
        var addRemove = "#comp-skill-field" + (comp_skill_form_next);
        comp_skill_form_next = comp_skill_form_next + 1;
        var newIn = '<div id="comp-skill-field'+ comp_skill_form_next +'" class="row p-b-5 p-t-25 text-right"><!-- Text input--><div class="row m-0">	<label class="col-md-2 col-md-offset-2 control-label" for="action_id">ชื่อโปรแกรม :</label><div class="col-md-8"><input name="comp-skill-name" type="text" placeholder="" class="form-control input-md w-400-px">	</div></div></div>';
        var newInput = $(newIn);
        var removeBtn = '<div class="row"><div class="col-md-9 col-offset-md-3 text-right"><button id="comp-skill-remove' + (comp_skill_form_next - 1) + '" class="btn btn-danger skill-remove" >Remove</button></div></div>';
        var removeButton = $(removeBtn);
        $(addto).after(newInput);
        $(addRemove).after(removeButton);
        $("#comp-skill-field" + comp_skill_form_next).attr('data-source',$(addto).attr('data-source'));
        $("#count").val(comp_skill_form_next);
        $('.skill-remove').click(function(e){
            e.preventDefault();
            var fieldNum = this.id.charAt(this.id.length-1);
            var fieldID = "#comp-skill-field" + fieldNum;
            console.log(fieldID);
            $(this).remove();
            $(fieldID).remove();
        });
    });

    var exp_form_next = 0;
    $("#exp-form-add-more").click(function(e){
        console.log("exp-form-add-more");
        e.preventDefault();
        var addto = "#exp-field" + exp_form_next;
        var addRemove = "#exp-field" + (exp_form_next);
        exp_form_next = exp_form_next + 1;
        var newIn = '<div id="exp-field'+ exp_form_next +'" class="p-t-25"><div><label class="col-md-2 col-md-offset-2 control-label" for="action_id">ปี พ.ศ. :</label>  <div class="col-md-8"><input name="exp-year" type="exp-year" placeholder="25xx" class="form-control input-md w-75-px"></div></div><br><br><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">ชื่อสถานที่ทำงาน :</label>  <div class="col-md-8"><input id="company-name" name="company-name" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><br><br><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">ตำแหน่ง :</label>  <div class="col-md-8"><input name="position" id="position" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><br><br><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">สาเหตุที่ออก :</label>  <div class="col-md-8"><input name="reason-for-leaving" id="reason-for-leaving" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><br><br><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">เงินเดือน :</label>  <div class="col-md-8"><input name="income" id="income" type="text" class="form-control input-md w-75-px"></div></div><br><br></div>';
        var newInput = $(newIn);
        var removeBtn = '<div class="row"><div class="col-md-9 col-offset-md-3 text-right"><button id="exp-remove' + (exp_form_next - 1) + '" class="btn btn-danger exp-remove" >Remove</button></div></div>';
        var removeButton = $(removeBtn);
        $(addto).after(newInput);
        $(addRemove).after(removeButton);
        $("#exp-field" + exp_form_next).attr('data-source',$(addto).attr('data-source'));
        $("#count").val(exp_form_next);
        $('.exp-remove').click(function(e){
            e.preventDefault();
            var fieldNum = this.id.charAt(this.id.length-1);
            var fieldID = "#exp-field" + fieldNum;
            console.log(fieldID);
            $(this).remove();
            $(fieldID).remove();
        });
            
    });

    var contact_person_form_next = 0;
    $("#contact-person-form-add-more").click(function(e){
            console.log("contact-person-form-add-more");
            e.preventDefault();
            var addto = "#contact-person-field" + contact_person_form_next;
            var addRemove = "#contact-person-field" + (contact_person_form_next);
            contact_person_form_next = contact_person_form_next + 1;
            var newIn = '<div id="contact-person-field'+ contact_person_form_next +'" class="p-t-25"><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">ชื่อ :</label> <div class="col-md-8"><input id="contact-person-first-name" name="contact-person-first-name" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><br><br><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">นามสกุล :</label> <div class="col-md-8">      <input id="contact-person-last-name" name="contact-person-last-name" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><br><br><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">ความสัมพันธ์ :</label> <div class="col-md-8">      <input name="contact-person-relation" id="contact-person-relation" type="text" placeholder="" class="form-control input-md w-400-px"></div></div><br><br><div><label class="col-md-2 col-md-offset-2 control-label" for="discipline">ที่อยุ่ที่ทำงาน :</label> <div class="col-md-8"><input name="contact-person-office-address" id="contact-person-office-address" type="text" placeholder="" class="form-control input-md w-400-px">     </div>      </div>      <br><br>      <div>     <label class="col-md-2 col-md-offset-2 control-label" for="discipline">เบอร์โทรศัพท์ :</label> <div class="col-md-8">      <input name="contact-person-tel" id="contact-person-tel" type="text" placeholder="0xx-xxx-xxxx" class="form-control input-md w-100-px">     </div>      </div> <br><br> <div>     <label class="col-md-2 col-md-offset-2 control-label" for="discipline">ตำแหน่ง :</label> <div class="col-md-8"><input name="contact-person-position" id="contact-person-position" type="text" class="form-control input-md w-200-px"></div></div><br><br></div>';
            var newInput = $(newIn);
            var removeBtn = '<div class="row"><div class="col-md-9 col-offset-md-3 text-right"><button id="contact-person-remove' + (contact_person_form_next - 1) + '" class="btn btn-danger contact-person-remove" >Remove</button></div></div>';
            var removeButton = $(removeBtn);
            $(addto).after(newInput);
            $(addRemove).after(removeButton);
            $("#contact-person-field" + contact_person_form_next).attr('data-source',$(addto).attr('data-source'));
            $("#count").val(contact_person_form_next);
            $('.contact-person-remove').click(function(e){
                e.preventDefault();
                var fieldNum = this.id.charAt(this.id.length-1);
                var fieldID = "#contact-person-field" + fieldNum;
                console.log(fieldID);
                $(this).remove();
                $(fieldID).remove();
            });
            
    });

    $("#ever-accused").click(function(e){
        $("#accused-content").removeClass("้hide").addClass("show");
    });

    $("#never-accused").click(function(e){
        $("#accused-content").removeClass("show").addClass("hide");
    });

    $("#can-shift-work").click(function(e){
        $("#shift-work-content").removeClass("show").addClass("hide");
    });

    $("#cannot-shift-work").click(function(e){
        $("#shift-work-content").removeClass("้hide").addClass("show");
    });
    
    $("#has-guarantor").click(function(e){
        $("#guarantor-content").removeClass("show").addClass("hide");
    });

    $("#not-has-guarantor").click(function(e){
        $("#guarantor-content").removeClass("้hide").addClass("show");
    });

    $("#never-sick").click(function(e){
        $("#sick-content").removeClass("show").addClass("hide");
    });

    $("#ever-sick").click(function(e){
        $("#sick-content").removeClass("้hide").addClass("show");
    });
});

function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
}
function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
}
