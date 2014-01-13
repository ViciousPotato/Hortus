var holder = document.getElementById('holder'),
    state  = document.getElementById('status');

/*
if (typeof window.FileReader === 'undefined') {
  state.className = 'fail';
} else {
  state.className = 'success';
  state.innerHTML = 'File API & FileReader available';
}
*/
 
holder.ondragover = function (evt) {
  evt.stopPropagation();
  evt.preventDefault();
  this.className = 'hover'; 
  return false; 
};

holder.ondragend = function () {
  this.className = '';
  return false;
};

holder.ondrop = function (e) {
  e.preventDefault();

  var file   = e.dataTransfer.files[0],
      reader = new FileReader();

  reader.onload = function (event) {
    console.log(event.target);
    console.log(event.target.result);
  };

  reader.readAsDataURL(file);

  return false;
};
