package fr.marodeur.expertbuild.api;

/**
 *
 *  	JSch 0.0.* was released under the GNU LGPL license.  Later, we have switched
 * 		over to a BSD-style license.
 *
 * 		------------------------------------------------------------------------------
 * 		Copyright (c) 2002-2015 Atsuhiko Yamanaka, JCraft,Inc.
 * 		All rights reserved.
 *
 * 		Redistribution and use in source and binary forms, with or without
 * 		modification, are permitted provided that the following conditions are met:
 *
 * 		1. Redistributions of source code must retain the above copyright notice,
 * 		this list of conditions and the following disclaimer.
 *
 * 		2. Redistributions in binary form must reproduce the above copyright
 * 		notice, this list of conditions and the following disclaimer in
 * 		the documentation and/or other materials provided with the distribution.
 *
 * 		3. The names of the authors may not be used to endorse or promote products
 * 		derived from this software without specific prior written permission.
 *
 * 		THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * 		INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * 		FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL JCRAFT,
 * 		INC. OR ANY CONTRIBUTORS TO THIS SOFTWARE BE LIABLE FOR ANY DIRECT, INDIRECT,
 * 		INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * 		LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * 		OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * 		LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * 		NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * 		EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.MessageBuilder;
import com.jcraft.jsch.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.ServerOperator;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class TransferSchema {

	private static final MessageBuilder message = Main.getInstance().getMessageConfig();

	public TransferSchema(final @NotNull Player p, String nameFile, String ip, int Port, String User,
						  String PassWord, String serverName) {

		final Logger log = Logger.getLogger("Expert-Build");
		BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, true);

		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

			JSch jsch = new JSch();
			Session session;
			try {

				Bukkit.getOnlinePlayers().stream()
						.filter(ServerOperator::isOp)
						.forEach(player -> player.sendMessage(Main.prefix + message.getDontRestart()));

				session = jsch.getSession(User, ip, Port);
				session.setConfig("StrictHostKeyChecking", "no");
				session.setPassword(PassWord);
				session.connect();

				Channel channel = session.openChannel("sftp");
				channel.connect();
				ChannelSftp sftpChannel = (ChannelSftp) channel;

				sftpChannel.put("plugins/FastAsyncWorldEdit/schematics/" + p.getUniqueId() + "/" + nameFile, "plugins/FastAsyncWorldEdit/schematics/Imported-" + nameFile);

				//sftpChannel.get("plugins/FastAsyncWorldEdit/" + NameFile);

				sftpChannel.exit();
				session.disconnect();
				brushBuilder.sendMessage(message.getSuccesTransfert(nameFile, serverName));

				log.info(message.getTransfertLog(nameFile, p.getName(), serverName));

			} catch (JSchException e) {
				e.printStackTrace();
				brushBuilder.sendMessage(message.getErrorJschException());

			} catch (SftpException e) {
				e.printStackTrace();
				brushBuilder.sendMessage(message.getErrorSftpException());
			}
		});
	}
}