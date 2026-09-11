package listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

@WebListener
public class MyRequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(final ServletRequestEvent sre) {
        final StringBuffer url = ((HttpServletRequest) sre.getServletRequest()).getRequestURL();
        System.out.println("Requête créée, URL : " + url);
    }
}
