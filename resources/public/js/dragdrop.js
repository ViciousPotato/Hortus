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
    // Request for md5 of file and redirect to /code/:md5
    $.post('/code', {'code': event.target.result}, function(data) {
      alert(data);
    });
  };

  reader.readAsDataURL(file);

  return false;
};
