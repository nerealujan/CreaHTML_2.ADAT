import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ClasePpal {

	public static void main(String[] args) {
		ArrayList lista_pisos=new ArrayList();
		String ruta="C:\\pisos\\";
		lista_pisos=obtenerImagenes(ruta+"\\imagenes");
		lista_pisos=obtenerDescripciones(ruta+"\\descripciones", lista_pisos);
		//String html_pagina=crearHTML(nombre_imagenes);
		String html_pagina=crearHTMLCompleto(lista_pisos);
		generarFichero(ruta, html_pagina);
		
	}

	private static String crearHTMLCompleto(ArrayList<Piso> lista_pisos) {
		String html="<html><head></head><body>";
		for (int i=0; i<lista_pisos.size(); i++)
		{
			Piso p=lista_pisos.get(i);
			html+="<img src='imagenes\\"+p.getRuta_imagen()+"'><br>";
			html+="<p>"+p.getDescripcion()+"</p><br><br>";
		}
		html+="</body></html>";
		return html;
	}

	private static ArrayList obtenerDescripciones(String ruta, ArrayList lista_pisos) {
		// TODO Auto-generated method stub
		String nombre_fichero="descripciones.txt";
		try {
			FileReader fr=new FileReader(ruta+"\\"+nombre_fichero);
			BufferedReader br=new BufferedReader(fr);
			String linea=br.readLine();
			int i=0; 
			while(linea!=null)
			{
				Piso p=(Piso)lista_pisos.get(i);
				p.setDescripcion(linea);
				linea=br.readLine();
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista_pisos;
	}

	private static void generarFichero(String ruta, String html_pagina) {
		String nombre_fichero="inmobiliaria.html";
		try {
			FileWriter fw=new FileWriter(ruta+nombre_fichero, false);
			fw.write(html_pagina);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static String crearHTML(String[] nombre_imagenes) {
		String html="<html><head></head><body>";
		for (int i=0; i<nombre_imagenes.length; i++)
		{
			html+="<img src='imagenes\\"+nombre_imagenes[i]+"'><br>";
		}
		html+="</body></html>";
		return html;
	}

	private static ArrayList obtenerImagenes(String ruta) {
		File f=new File(ruta);
		String[] lista_imagenes=f.list();
		ArrayList lista=new ArrayList<>();
		for (int i=0; i<lista_imagenes.length; i++)
		{
			Piso p=new Piso();
			p.setRuta_imagen(lista_imagenes[i]);
			lista.add(p);
		}
		return lista;
	}

}
