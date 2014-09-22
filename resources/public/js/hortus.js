$(document).ready(function() {
  var pieces = location.href.split('/');
  var md5 = pieces[pieces.length-1];

  $('#title h1').editable("/annotation/" + md5 + "/title", {
    indicator: 'Saving...'
  });
  $('#section-1 .annotation').editable("/annotation/" + md5 + "/content", {
    indicator: 'Saving...',
    type: 'textarea',
    submit: 'OK',
    cancel: 'Cancel'
  });
});