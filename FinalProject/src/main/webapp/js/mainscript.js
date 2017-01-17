var textCount=1;

function createOption(){
    var text = document.createElement("input");    
    var deleteButton=document.createElement("input"); 
    
    text.type="text";
    text.name="optionText";
    text.id="optionText"+textCount;
    text.className="form-control";
    text.placeholder="Вариант ответа"; 
    
    deleteButton.type="button";
    deleteButton.className="delete-button";
    deleteButton.id="delete-button"+textCount;
    deleteButton.value="Удалить";
    deleteButton.setAttribute("onClick", 'deleteOption("'+deleteButton.id+'")');
    
    textCount++;
    
    var creater=document.getElementById("create-option-button");
    var form=document.getElementById("form-signin");
    form.insertBefore(text, creater); 
    form.insertBefore(deleteButton, creater); 
}

function deleteOption(value){
    value=value.replace('delete-button','');
    var form=document.getElementById("form-signin");
    form.removeChild(document.getElementById("optionText"+value));
    form.removeChild(document.getElementById("delete-button"+value));
}

function chooseImage(name){
    var imageName=document.getElementById("inputImage");
    imageName.value=name;   
}