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
		$('.graph .line-layer .boxs').find('.line-box').click(function() {
            $(this).addClass('active').siblings().removeClass('active');
            PlayBack.refreshTree(PlayBack.data.packets[$(this).index()].protos);
            PlayBack.refreshAsciiiData(PlayBack.data.packets[$(this).index()].frame);
		}).eq(0).click();

		var baseWidth = 300;
		var margin_left_diff = 243;

		/*****测试代码开始******/
		$('.line-layer .line-box').each(function(index) {
			$(this).css('marginLeft', parseInt($(this).css('marginLeft')) + margin_left_diff * index);
		});
		/*****测试代码结束*********/


		$('.boxs').width(($('.line-layer .line-box').length + 0.75) * margin_left_diff);

		var lastScrollLeft = 0;
		$('.line-layer').scroll(function(event) {
			/* Act on the event */
			var _scrollLeft = $(this).scrollLeft();
			if (lastScrollLeft != _scrollLeft) {
				var back_layer = $('.back-layer');
				$(back_layer).css('marginLeft', parseInt($(back_layer).css('marginLeft')) - (_scrollLeft - lastScrollLeft));
				lastScrollLeft = _scrollLeft;
			}
		});


	},
	createGraph: function() {
		// body...
		var table = $('.graph .back-layer table');
		var trs = table.find('tr').empty();
		var elementNameTr = trs[0];
		var elementIconTr = trs[1];
		var elementLineTr = trs[2];

		var packets = PlayBack.data.packets;
		var size = packets.length;

        for (var t = 0; t < 10; t++)
		for (var i in packets) {
			var packet = packets[i];
			var brief = packet.brief;

			$(elementNameTr).append('<td>eNodeB_' + (2 * i + 1) + '</td>');
			$(elementIconTr).append('<td><span class="icon-enodeb"></span></td>');
			$(elementLineTr).append('<td><i></i></td>');

			$(elementNameTr).append('<td>eNodeB_' + (2 * i + 2) + '</td>');
			$(elementIconTr).append('<td><span class="icon-enodeb"></span></td>');
			$(elementLineTr).append('<td><i></i></td>');

			PlayBack.createLine(brief, packet.direction);

		}
	},

	createLine: function(brief, direction) {
		var boxs = $('.graph .line-layer .boxs');
		var lineBox = '<div class="line-box">' + '<p class="time">' + brief.time + '</p>' + '<div class="line">' + '<div class="triangle"></div>' + '</div>' + '<p class="text">' + brief.info + '</p>' + '</div>';
		lineBox = $(lineBox).addClass(PlayBack.getDirction);

		boxs.append(lineBox);

	},

    getDirction: function(value) {
        return 'right';
    },

	refreshTree: function(protos) {
		var data = PlayBack.getTreeData(protos);
        $('#frameTree').tree({
            data:data,
            onClick: function(node) {
                console.log(node);
            }
        });
	},
	getTreeData: function(protos) {
        var data = [];
        var getChildren = function(index, proto) {
            var children = [], fields = proto.fields, len = fields.length;
            if (len === 0) return children;

            for (var i = 0; i < len; i++) {
                var field = fields[i];
                var __data = {
                    id: index + '_' + i,
                    text: field.showName,
                    // pos: field.pos,
                    // size: field.size,
                    children: getChildren(i, field)
                };
                children.push(__data);

                // ----
                if (field.size) {
                    __data.pos = field.pos;
                    __data.size = field.size;
                }
            }
            return children;
        };

        for (var i = 0, len = protos.length; i < len; i++) {
            var proto = protos[i];
            var __data = {
                id: i,
                text: proto.showName,
                children: getChildren(i, proto)
            };

            if (proto.size) {
                __data.pos = proto.pos;
                __data.size = proto.size;
            }
            data.push(__data);
        }
        return data;
	},

    refreshAsciiiData: function(frame) {
        var rows = frame.value.split('\n');
        var lineNumberDiv = $('.bottom-right .line-number').empty(),
            hexDiv = $('.bottom-right .hex').empty(),
            asciiDiv = $('.bottom-right .ascii').empty();

        for (var i = 0, len = rows.length; i < len; i++) {
            var hexRow = $('<div class="row" />'),
                asciiRow = $('<div class="row" />');
            hexDiv.append(hexRow);
            asciiDiv.append(asciiRow);
            PlayBack.processRow(hexRow, asciiRow, rows[i]);
            lineNumberDiv.append($('<div class="row" />').append('<span>' + PlayBack.generateRowNumber(i) + '</span>'));
        }
    },

    processRow: function(hexRow, asciiRow, value) {
        var vals = value.split(' ');
        for (var i = 0, len = vals.length; i < len; i++) {
            hexRow.append('<span>' + vals[i] + '</span>');
            asciiRow.append('<span>' + PlayBack.formatAsciiCode(vals[i]) + '</span>');
        }
    },

    generateRowNumber: function(rowIndex) {
        var hexRowNumber = rowIndex.toString(16);
        switch (hexRowNumber.length) {
            case 1:
                return '00' + hexRowNumber + '0';
            case 2:
                return '0' + hexRowNumber + '0';
            case 3:
                return hexRowNumber + '0';
        }
    },

    formatAsciiCode: function(hexCode) {
        var intVal = parseInt(hexCode, 16);
        if (intVal <= 31 || intVal >= 127) return '.';
        if (intVal == 32) return '&nbsp';
        return String.fromCharCode(intVal);
    }


};
