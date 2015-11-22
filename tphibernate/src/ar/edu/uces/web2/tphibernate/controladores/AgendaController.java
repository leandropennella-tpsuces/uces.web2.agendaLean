package ar.edu.uces.web2.tphibernate.controladores;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.uces.web2.tphibernate.dao.EventoDAO;
import ar.edu.uces.web2.tphibernate.modelo.base.Evento;
import ar.edu.uces.web2.tphibernate.modelo.base.Usuario;


@SessionAttributes("usuario")
@Controller
public class AgendaController {
	
	private EventoDAO eventoDAO;
	
	@Autowired
	public void setEventoDAO(EventoDAO eventoDAO) {
		this.eventoDAO = eventoDAO;
	}
	
	@RequestMapping(value = "/agenda/mostrarCalendario")
	public ModelAndView mostrarCalendario(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("usuario") Usuario usuario) {
		//Date fecha = new Date();
		/*Calendar calendar = Calendar.getInstance();
		Calendar gcalendar =  new GregorianCalendar();
		calendar.get
		SimpleDateFormat d=new SimpleDateFormat("dd-MM-yyyy");
		Date fecha=d.parse(calendar.gdate.DAY_OF_MONTH+"-"+gcalendar.MONTH+"-"+gcalendar.YEAR, new ParsePosition(0));*/
		Map<String,List<Evento>> semana=new HashMap<String,List<Evento>>();
		List<Evento> eventos;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		for(int i=0;i<7;i++){
			
			String sfecha = sdf.format(new Date());
			Date fecha=sdf.parse(sfecha, new ParsePosition(0));
			eventos=eventoDAO.getByAutorAndDate(usuario, fecha );
			semana.put("", eventos);
		}
		return new ModelAndView("/views/agenda/calendario.jsp","semana", semana);
	}
}
