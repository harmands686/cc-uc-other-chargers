$( "#load-data-btn" ).click(function() {
    var subs = $("#numberofSubscribers").val();
    subsInt = parseInt(subs);
    if (subsInt == 0 || subsInt > 30){
        alert("Subscriber Number should be greater than 0 and less than 31")
        return
    }
    window.location.href = "/load/"+subs;
  });

  $( "#btn-submit" ).click(function() {
    var xml = "<CustomerOrder>";
    var CustomerOrder = [];
    xml = xml + "<OrderRefNumber>"+ $("#OrderRefNumber").val() + "</OrderRefNumber>";
    xml = xml + "<CustomerName>"+ $("#CustomerName").val() + "</CustomerName>";
    xml = xml + "<CustomerAccountId>"+ $("#CustomerAccountId").val() + "</CustomerAccountId>";

    CustomerOrder['Subscriber'] = [];
    xml = xml + "<SubscriberList>";
    $(".subscriberContainer").each(function(index) {
        xml = xml + "<Subscriber>";
        $( this ).find(':input').each(function(index2) {
		  var className = $( this ).attr('class');
          var element = className.split(" ")[1];
          xml = xml + "<"+element+">";
          xml = xml +  $( this ).val(); + "</"+element+">";
          xml = xml +   "</"+element+">";
        })
        xml = xml + "</Subscriber>";
    });
    xml = xml + "</SubscriberList>";
    xml = xml + "</CustomerOrder>";
    //xmlDoc = $.parseXML( xml ),
    //$xml = $( xmlDoc );
    console.log(xml)
    var prettyXML = formatXml(xml);
    $("#textPayload").val(prettyXML);
    console.log(prettyXML)


    $.ajax({
        type: "POST",
        url: "http://[...]",
        dataType: "xml",
        contentType: "application/xml",
        data: xml,
        success: function (res) {
            alert("Successfully Sent the Request");
        },
        error: function (res) {
            alert("Something is not working! " + res.statusText);
        }
    });
  });

function formatXml(xml, tab) { // tab = optional indent value, default is tab (\t)
    var formatted = '', indent= '';
    tab = tab || '\t';
    xml.split(/>\s*</).forEach(function(node) {
        if (node.match( /^\/\w/ )) indent = indent.substring(tab.length); // decrease indent by one 'tab'
        formatted += indent + '<' + node + '>\r\n';
        if (node.match( /^<?\w[^>]*[^\/]$/ )) indent += tab;              // increase indent
    });
    return formatted.substring(1, formatted.length-3);
}

function auto_grow(element) {
    element.style.height = "5px";
    element.style.height = (element.scrollHeight)+"px";
}