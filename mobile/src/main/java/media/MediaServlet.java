package media;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ws.media.MediaService;
import ws.media.MediaWs;

import java.io.IOException;

/**
 * Servlet implementation class MediaServlet
 */
@WebServlet("/MediaServlet")
public class MediaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MediaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private final MediaWs mediaPort = new MediaService().getMediaPort();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      String name = req.getParameter("name");
      if (name == null || name.isBlank()) {
        resp.sendError(400);
        return;
      }

      try {
        byte[] data = mediaPort.obtenerImagen(name);

        // tipo MIME básico
        String mime = getServletContext().getMimeType(name);
        resp.setContentType(mime != null ? mime : "application/octet-stream");

        resp.getOutputStream().write(data);
      } catch (Exception e) {
        resp.sendError(404);
      }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
