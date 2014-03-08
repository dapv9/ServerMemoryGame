import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	Socket skCliente;
	ServerSocket skServidor;
	String datareceived, substring1, substring2;
	int puntaje[];
        int puntero=0;
        int may[];
	final int PUERTO = 7000;// Puerto que utilizara el servidor
							// mismo en el cliente
	String IP_client;
	Mensaje_data mdata = null;
	ObjectOutputStream oos = null;
	String TimeStamp;

	Server() {

		try {
			System.out.println("************ SERVER ****************");
			// creamos server socket
			skServidor = new ServerSocket(PUERTO);
			System.out.println("Escuchando el puerto " + PUERTO);
			System.out.println("En Espera....");

			TimeStamp = new java.util.Date().toString();

			try {
				// Creamos socket para manejar conexion con cliente
				skCliente = skServidor.accept(); // esperamos al cliente
				// una vez q se conecto obtenemos la ip
				IP_client = skCliente.getInetAddress().toString();
				System.out.println("[" + TimeStamp + "] Conectado al cliente "
						+ "IP:" + IP_client);

				while (true) {
					// Manejamos flujo de Entrada
					ObjectInputStream ois = new ObjectInputStream(
					skCliente.getInputStream());
					// Cremos un Objeto con lo recibido del cliente
					Object aux = ois.readObject();// leemos objeto

					// si el objeto es una instancia de Mensaje_data
					if (aux instanceof Mensaje_data) {
						// casteamos el objeto
						mdata = (Mensaje_data) aux;

						// Analizamos el mensaje recibido
						// si no es el mensaje FINAL
						if (!mdata.last_msg) {
									
							// Imprimimos el mensaje de texto
							System.out.println("[" + TimeStamp + "] "
									+ "Mensaje de [" + IP_client + "]--> "
									+ mdata.texto);
							
						} else {// cerramos socket
							skCliente.close();
							ois.close();
							System.out.println("["+ TimeStamp
											+ "] Last_msg detected Conexion cerrada");
							break;
						}
					} else {
						// Si no es del tipo esperado, se marca error
						System.err.println("Mensaje no esperado ");
					}
				}
			} catch (Exception e) {
				System.out.println("Error ");
			}
		} catch (Exception e) {
			System.out.println("Error ");
		}
	}

	public static void main(String[] args) {
		new Server();
	}
	
	public void setPuntaje(String punt){
		puntaje[puntero]=Integer.parseInt(punt);
		puntero++;
        }
        
        public void ordenaMayores(){
		int aux;
                for (int l=0;l<=10;l++){
                    may[l]=0;
                    }
                
		for(int h=0;h<=puntero;h++){
			aux=puntaje[h];
			if(aux>may[1]){
				may[10]=may[9];
				may[9]=may[8];
				may[8]=may[7];
				may[7]=may[6];
				may[6]=may[5];
				may[5]=may[4];
				may[4]=may[3];
				may[3]=may[2];
				may[2]=may[1];
				may[1]=aux;
			}else if(aux>may[2]){
				may[10]=may[9];
				may[9]=may[8];
				may[8]=may[7];
				may[7]=may[6];
				may[6]=may[5];
				may[5]=may[4];
				may[4]=may[3];
				may[3]=may[2];
				may[2]=aux;
			}else if(aux>may[3]){
				may[10]=may[9];
				may[9]=may[8];
				may[8]=may[7];
				may[7]=may[6];
				may[6]=may[5];
				may[5]=may[4];
				may[4]=may[3];
				may[3]=aux;
			}else if(aux>may[4]){
				may[10]=may[9];
				may[9]=may[8];
				may[8]=may[7];
				may[7]=may[6];
				may[6]=may[5];
				may[5]=may[4];
				may[4]=aux;
			}else if(aux>may[5]){
				may[10]=may[9];
				may[9]=may[8];
				may[8]=may[7];
				may[7]=may[6];
				may[6]=may[5];
				may[5]=aux;
			}else if(aux>may[6]){
				may[10]=may[9];
				may[9]=may[8];
				may[8]=may[7];
				may[7]=may[6];
				may[6]=aux;
			}else if(aux>may[7]){
				may[10]=may[9];
				may[9]=may[8];
				may[8]=may[7];
				may[7]=aux;
			}else if(aux>may[8]){
				may[10]=may[9];
				may[9]=may[8];
				may[8]=aux;
			}else if(aux>may[9]){
				may[10]=may[9];
				may[9]=aux;
			}else if(aux>may[10]){
				may[10]=aux;
			}
			
		}	
	}
	
        public int[] getPuntaje(){
            	ordenaMayores();
	return may;
	}
}
