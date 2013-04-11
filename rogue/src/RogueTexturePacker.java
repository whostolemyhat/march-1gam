import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;


public class RogueTexturePacker {
	//http://steigert.blogspot.co.uk/2012/03/7-libgdx-tutorial-texture-image-atlas.html
	private static final String INPUT_DIR = "etc/images";
	private static final String OUTPUT_DIR = "../rogue-android/assets/image-atlases";
	private static final String PACK_FILE = "pages-info";
	
	public static void main(String[] args) {
		Settings settings = new Settings();
		settings.paddingX = 2;
		settings.paddingY = 2;
		settings.edgePadding = false;
		
		settings.maxWidth = 512;
		settings.maxHeight = 512;
		
		// don't repack a group when no changes were made to it
//        settings.incremental = true;
        
     // pack the images
        TexturePacker2.process( settings, INPUT_DIR, OUTPUT_DIR, PACK_FILE );
	}
}
