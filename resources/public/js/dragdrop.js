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

  var content = event.target.result.split('base64,')[1];

  $.post('/code', {
    filename: file.name,
    content:  content
  }).done(function(data) {
    location.href="/code/"+data;
  }).fail(function() {
    alert('Something wrong posting to /code');
  });
  };

  reader.readAsDataURL(file);

  return false;
};
