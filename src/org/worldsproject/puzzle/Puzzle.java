package org.worldsproject.puzzle;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Puzzle
{
	private static final Random RAN = new Random();
	
	private Piece[][] puzzle;
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	private int display_width;
	private int display_height;
	private int piece_width;
	private int piece_height;
	
	public Puzzle(Bitmap[] images, int width, int height, int display_width, int display_height)
	{
		this.display_height = display_height;
		this.display_width = display_width;
		
		piece_width = images[0].getWidth();
		piece_height = images[0].getHeight();
		
		puzzle = new Piece[width][height];
		
		int loc = 0;
		
		for(int y = 0; y < puzzle[0].length; y++)
		{
			for(int x = 0; x < puzzle.length; x++)
			{
				puzzle[x][y] = new Piece(images[loc]);
				pieces.add(puzzle[x][y]);
				loc++;
			}
		}
		
		for(int x = 0; x < puzzle.length; x++)
		{
			for(int y = 0; y < puzzle[x].length; y++)
			{
				//Top
				if(y-1 >= 0)
				{
					puzzle[x][y].setTop(puzzle[x][y-1]);
				}
				//Right
				if(x+1 < width)
				{
					puzzle[x][y].setRight(puzzle[x+1][y]);
				}
				//Bottom
				if(y+1 < height)
				{
					puzzle[x][y].setBottom(puzzle[x][y+1]);
				}
				//Left
				if(x-1 >= 0)
				{
					puzzle[x-1][y].setLeft(puzzle[x-1][y]);
				}
			}
		}
		
		shuffle();
	}
	
	public void shuffle()
	{
		int maxX = display_width - piece_width;
		int maxY = display_height - piece_height;
		
		for(int i = 0; i < puzzle.length; i++)
		{
			for(int j = 0; j < puzzle[i].length; j++)
			{
				puzzle[i][j].setX(RAN.nextInt(maxX));
				puzzle[i][j].setY(RAN.nextInt(maxY));
			}
		}
	}
	
	public void draw(Canvas c)
	{
		for(int i = 0; i < puzzle.length; i++)
		{
			for(int j = 0; j < puzzle[i].length; j++)
			{
				puzzle[i][j].draw(c);
			}
		}
	}
	
	public ArrayList<Piece> getPieces()
	{
		return this.pieces;
	}
}
