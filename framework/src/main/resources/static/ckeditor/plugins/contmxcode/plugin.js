CKEDITOR.plugins.add('contmxcode', {
	requires: 'widget',
	icons: 'contmxcode',
	onLoad: function() {
        CKEDITOR.addCss(
            '.cont-mxcode {' +
            	'background: #f6f7f8;' +
            	'width: 140px; ' +
            	'height: 40px;' + 
            	'color: #999;' + 
            	'font-size: 15px;' +
            	'font-weight: bold;' +
                'line-height: 40px;' + 
            	'border: 1px solid #eee;' +
            	'display: inline-block;' +
                'text-align: center;' + 
                'vertical-align: middle; ' + 
        	'}' +
        	'.cont-mxcode:before {' + 
	            'content: "BARCODE/QRCODE";' +
	        '}'
        );
    },
	init: function(editor) {
		CKEDITOR.dialog.add('contmxcode', this.path + 'dialogs/contmxcode.js');

		editor.widgets.add('contmxcode', {
			allowedContent: 'span(!cont-mxcode-wrap);' +
				'span(!cont-mxcode){width}',

			xrequiredContent: 'span(cont-mxcode-wrap)',

			template: '<span class="cont-mxcode-wrap">' +
				'<span class="cont-mxcode">&nbsp;</span>' +
				'</span>',

			button: '合同条形码/二维码',

			dialog: 'contmxcode',

			upcast: function(element) {
				return element.name == 'span' && element.hasClass('cont-mxcode-wrap');
			},

			init: function() {
				var mxcode = CKEDITOR.dom.element
					.createFromHtml(this.element.getHtml());
	
				//mxfield
				var mxfield = mxcode.getAttribute('data-cont-mxfield');
				if(mxfield){
					this.setData('mxfield', mxfield);
				}
	
				//mxtype
				var mxtype = mxcode.getAttribute('data-cont-mxtype');
				if(mxtype){
					this.setData('mxtype', mxtype);
				}
			},

			data: function() {
				var mxcode = CKEDITOR.dom.element
					.createFromHtml(this.element.getHtml());
	
				//mxfield
				if(!this.data.mxfield){
					mxcode.removeAttribute('data-cont-mxfield');
				}else{
					mxcode.setAttribute('data-cont-mxfield', this.data.mxfield);
				}
	
				//mxtype
				if(!this.data.mxtype){
					mxcode.removeAttribute('data-cont-mxtype');
				}else{
					mxcode.setAttribute('data-cont-mxtype', this.data.mxtype);
				}
	
				this.element.setHtml(mxcode.getOuterHtml());
			}
		});
	}
});