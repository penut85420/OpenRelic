package main.Custom.Listener;

import java.awt.event.*;

public class MouseClickListener implements MouseListener {
	
	public void mouseClick(MouseEvent e) { }

	@Override
	public void mouseClicked(MouseEvent e)  { mouseClick(e); }
	@Override
	public void mouseEntered(MouseEvent e) { }
	@Override
	public void mouseExited(MouseEvent e) { }
	@Override
	public void mousePressed(MouseEvent e)  { mouseClick(e); }
	@Override
	public void mouseReleased(MouseEvent e) { mouseClick(e); }

}
