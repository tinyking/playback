// PlayBack.js
var PlayBack = {
    data: null,
    elements: [],
    init: function(data) {
      PlayBack.data = data;
      $('.graph .line-layer .boxs').empty();
      PlayBack.createGraph();
      PlayBack.completed();
    },
    completed: function() {
      $('.graph .line-layer .boxs').find('.line-box').click(function(index) {
        console.info($(this).text());
        $(this).addClass('active').siblings().removeClass('active');
      }).eq(0).click();
    },
    createGraph: function () {
      // body...
      var table = $('.graph .back-layer table');
      var trs =  table.find('tr').empty();
      var elementNameTr = trs[0];
      var elementIconTr = trs[1];
      var elementLineTr = trs[2];

      var packets = PlayBack.data.packets;
      var size = packets.length;

      for (var i in packets) {
        var packet = packets[i];
        var brief = packet.brief;

        $(elementNameTr).append('<td>eNodeB_' + (2*i + 1) + '</td>');
        $(elementIconTr).append('<td><span class="icon-enodeb"></span></td>');
        $(elementLineTr).append('<td><i></i></td>');

        $(elementNameTr).append('<td>eNodeB_' + (2*i + 2) + '</td>');
        $(elementIconTr).append('<td><span class="icon-enodeb"></span></td>');
        $(elementLineTr).append('<td><i></i></td>');

        PlayBack.createLine(brief);

      }
    },

    createLine: function(brief) {
      var boxs = $('.graph .line-layer .boxs');
      var lineBox = '<div class="line-box right">'
                    + '<p class="time">' + brief.time+ '</p>'
                    + '<div class="line">'
                    +   '<div class="triangle"></div>'
                    + '</div>'
                    + '<p class="text">' + brief.info + '</p>'
                    + '</div>';
      boxs.append(lineBox);
    }


};
