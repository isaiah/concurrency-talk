const async = require('async'),
      https = require('https');

const dois = ["10.1007/3-540-29623-9", "10.1007/978-3-642-38954-2", "10.1007/978-3-540-74339-2"]
var c = 0;
var funcs = [];
for (var i = 0; i < dois.length; i++) {
  var doi = dois[i];
  funcs.push(function(cb) {
    https.get("https://search.crossref.org/?q=" + doi, (res) => {
      const { statusCode } = res;
      if (statusCode == 200) {
        c++;
        cb();
      }
    });
  });
}

async.parallel(funcs, function() {
  console.log(c);
});
