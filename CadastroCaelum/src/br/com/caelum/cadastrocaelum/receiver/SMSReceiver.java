package br.com.caelum.cadastrocaelum.receiver;

import android.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.telephony.SmsMessage;
import android.widget.Toast;
import br.com.caelum.cadastrocaelum.dao.AlunoDAO;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
			Object[] mensagens = (Object[]) intent.getExtras().get("pdus");
			byte[] primeira = (byte[]) mensagens[0];
			SmsMessage 	sms = SmsMessage.createFromPdu(primeira);
			String telefone = sms.getDisplayOriginatingAddress();

			AlunoDAO alunoDAO = new AlunoDAO(context);
			boolean ehAluno = alunoDAO.isAluno(telefone);
			alunoDAO.close();
			
			//Toast.makeText(context, "SMS de aluno: "+telefone, Toast.LENGTH_LONG).show();
			
			if (ehAluno) {
				Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				Ringtone r = RingtoneManager.getRingtone(context, notification);
				r.play();
				Toast.makeText(context, "SMS de aluno cadastrado: "+telefone, Toast.LENGTH_LONG).show();
			}
	}
}