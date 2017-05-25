/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
    config.width = 800;
    config.height = 300;
    config.allowedContent = true;
	config.skin = 'bootstrapck';
    config.extraPlugins = 'lineheight,mxcode'; 
};
