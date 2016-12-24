package Server.Controller;

/**
 * Created by gioele on 16/02/16.
 */
public interface IController {

    void Attach(Observer o);

    void Detach(Observer o);

    void Notify();

}
