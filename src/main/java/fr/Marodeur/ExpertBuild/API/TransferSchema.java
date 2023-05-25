package fr.Marodeur.ExpertBuild.API;

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

import com.jcraft.jsch.*;
import fr.Marodeur.ExpertBuild.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.ServerOperator;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class TransferSchema {

	public TransferSchema(final @NotNull Player p, String NameFile, String ip, int Port, String User,
						  String PassWord, String ServerName) {

		final Logger log = Logger.getLogger("Expert-Build");

		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

			JSch jsch = new JSch();
			Session session;
			try {

				Bukkit.getOnlinePlayers().stream()
						.filter(ServerOperator::isOp)
						.forEach(player -> player.sendMessage(Main.prefix + "Connection to the server... please, don't reload ExpertBuild, don't restart the serveur"));

				session = jsch.getSession(User, ip, Port);
				session.setConfig("StrictHostKeyChecking", "no");
				session.setPassword(PassWord);
				session.connect();

				p.sendMessage(Main.prefix + "schematics transfer...");

				Channel channel = session.openChannel("sftp");
				channel.connect();
				ChannelSftp sftpChannel = (ChannelSftp) channel;

				sftpChannel.put("plugins/FastAsyncWorldEdit/schematics/" + p.getUniqueId() + "/" + NameFile, "plugins/FastAsyncWorldEdit/schematics/" + p.getUniqueId() + "/Imported-" + NameFile);

				//sftpChannel.get("plugins/FastAsyncWorldEdit/" + NameFile);

				sftpChannel.exit();
				session.disconnect();
				p.sendMessage(Main.prefix + "Transfer finish, successful upload file : Imported-" + NameFile + " on server : " + ServerName);

				log.info("NameFile imported Exported with succes by " + p + " on : " + ServerName);

			} catch (JSchException e) {
				e.printStackTrace();
				p.sendMessage(Main.prefix + "Error JSch-Exception, please see console");

			} catch (SftpException e) {
				e.printStackTrace();
				p.sendMessage(Main.prefix + "Error Sftp-Exception, please see console");
			}
		});
	}
}