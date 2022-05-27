package controller.command;

import controller.facade.Fachada;
import controller.facade.IFachada;

public abstract class AbstractCommand implements ICommand {
	protected IFachada fachada = new Fachada();	

}
