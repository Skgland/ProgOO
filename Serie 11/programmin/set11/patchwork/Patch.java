package programming.set11.patchwork;

import acm.graphics.GCompound;
import acm.graphics.GDimension;

import java.util.Random;

public class Patch extends GCompound {

	public final int SIDE_LENGTH;

	/**
	 * Generates a random DefaultPatch
	 *
	 * @param patch_side_length the side length of the patch
	 */
	public Patch(int patch_side_length) {
		SIDE_LENGTH = patch_side_length;
		DefaultPatches.values()[new Random().nextInt(DefaultPatches.values().length)].generatePatch(this);
		assert getSize().equals(new GDimension(patch_side_length, patch_side_length)) : "Das generierte GCompound ist zu groß!";
	}

	/**
	 * Generated a Patch
	 *
	 * @param patch_side_length the side length of the patch
	 * @param patch the IPatchGenerator used to generate the patch
	 * @throws IllegalStateException if the generated patch does not obey the SIDE_LENGTH
	 */
	public Patch(int patch_side_length, IPatchGenerator patch) {
		SIDE_LENGTH = patch_side_length;
		patch.generatePatch(this);
		if (!getSize().equals(new GDimension(SIDE_LENGTH, SIDE_LENGTH))) {
			throw new IllegalStateException("Generated Patch was bigger than allowed!");
		}
	}
}