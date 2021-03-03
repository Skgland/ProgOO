package programming.set11.patchwork;

import acm.graphics.GCompound;
import acm.graphics.GDimension;

import java.util.Random;

public class Patch extends GCompound {

		public final int SIDE_LENGTH;

		public Patch(int patch_side_length) {
			SIDE_LENGTH = patch_side_length;
			DefaultPatches.values()[new Random().nextInt(DefaultPatches.values().length)].generatePatch(this);
			assert getSize().equals(new GDimension(patch_side_length, patch_side_length)) : "Das generierte GCompound ist zu groß!";
		}

		//using this constructor you could create your own DefaultPatches
		public Patch(int patch_side_length,IPatchGenerator patch) {
			SIDE_LENGTH = patch_side_length;
			patch.generatePatch(this);
			if(!getSize().equals(new GDimension(patch_side_length, patch_side_length))) {
				throw new IllegalStateException("Generated Patch was bigger than allowed!");
			}
		}
	}