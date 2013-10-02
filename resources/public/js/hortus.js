$(document).ready(function() {
  $('#title h1').editable("/annotation/title", {
    indicator: 'Saving...'
  });
  $('#section-1 .annotation').editable("/annotation/content", {
    indicator: 'Saving...',
    type: 'textarea',
    submit: 'OK',
    cancel: 'Cancel',
    callback: function() {
      $('#section-1 .annotation p').editable({
        type: 'textarea'
      });
    }
  });
});