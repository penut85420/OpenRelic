package main.Custom.Listener;

import java.awt.event.*;

public class MouseSmoothClickListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e)  { }
	@Override
	public void mouseEntered(MouseEvent e) { }
	@Override
	public void mouseExited(MouseEvent e) { }
	@Override
	public void mousePressed(MouseEvent e)  { mouseClicked(e); }
	@Override
	public void mouseReleased(MouseEvent e) { mouseClicked(e); }

}
