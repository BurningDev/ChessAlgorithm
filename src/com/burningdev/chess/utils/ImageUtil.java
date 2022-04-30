/**
 * Author: BurningDev
 */
package com.burningdev.chess.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.pmw.tinylog.Logger;

import com.burningdev.chess.core.Figure;
import com.burningdev.chess.core.Fraction;

public class ImageUtil {
	private ImageUtil() {

	}
	
	public static BufferedImage getImageByFigure(Figure figur, Fraction fraction) {
		StringBuilder url = new StringBuilder();
		url.append("img\\");

		if (fraction == Fraction.COMPUTER) {
			url.append("b");
		} else {
			url.append("w");
		}

		url.append("_");

		switch (figur) {
		case KING:
			url.append("king");
			break;
		case QUEEN:
			url.append("queen");
			break;
		case BISHOP:
			url.append("bishop");
			break;
		case KNIGHT:
			url.append("knight");
			break;
		case ROOK:
			url.append("rook");
			break;
		case PAWN:
			url.append("pawn");
			break;
		}
		
		url.append("_png_noShadow.png");

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(url.toString()));
		} catch (IOException e) {
			Logger.error(e, e.getMessage());
		}

		return image;
	}
}
