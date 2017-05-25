CKEDITOR.dialog.add('contmxcode', function(editor) {
	
	var inputType = (function() {
		var contInputs = window.contInputs || [];
		
		var types = [];
		$.each(contInputs, function(i, item){
			var belongs;
			if(item.contTerms){
				var terms = item.contTerms;
				belongs = '(' + terms.title + ')';
			}
			types.push([item.name + (belongs || ''), item.id]);
		});

		return types;
	})();
	
	return {
		title: '条形码/二维码',
		minWidth: 400,
		minHeight: 120,
		contents: [{}]
//			[{
//			id: 'info',
//			elements: [{
//			    type: 'hbox',
//			    widths: ['28%', '72%'],
//			    children: [{
//					id: 'mxtype',
//					type: 'select',
//					label: '类型',
//					items: [['条形码', 'barcode'],['二维码', 'qrcode']],
//					setup: function(widget) {
//						this.setValue(widget.data.mxtype);
//					},
//					commit: function(widget) {
//						widget.setData('mxtype', this.getValue());
//					}
//				}, {
//					id: 'mxfield',
//					type: 'select',
//					label: '关联输入项',
//					items: inputType,
//					width: '260px',
//					setup: function(widget) {
//						this.setValue(widget.data.mxfield);
//					},
//					commit: function(widget) {
//						widget.setData('mxfield', this.getValue());
//					}
//				}]
//			}]
//		}]
	};
});