package com.example.bdgesttest.react;

public class InitialHtml {

    private StringBuilder stringBuilder;

    public InitialHtml(String title) {
        stringBuilder = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">")
                .append("<html xmlns=\"http://www.w3.org/1999/xhtml\">")
                .append("<head>")
                .append("<title>" + title + "</title>")
                .append("<meta charset=\"UTF-8\">")
                .append("<link rel=\"stylesheet\", type=\"text/css\", href=\"/dist/bundle.css\")>")
                .append("<link rel=\"icon\", type=\"image/png\", href=\"./book.png\")>")
                .append("</head>")
                .append("<body>")
                .append("<div id=\"reactEntryPoint\">")
                .append("<script src=\"/dist/bundle.js\">")
                .append("</script>")
                .append("</div>")
                .append("</body>")
                .append("</html>");
    }

    public String serialize() {
        return stringBuilder.toString();
    }

}
