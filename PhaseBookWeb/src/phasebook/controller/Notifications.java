package phasebook.controller;

import phasebook.user.PhasebookUser;

import java.util.ArrayList;
import java.util.List;

public class Notifications {
	
	private PhasebookUser user;
	
	Notifications(PhasebookUser user)
	{
		this.user=user;
	}
	
	public List<Object> checkForNotifications()
	{
		List<Object> notifications = new ArrayList<Object>();
		notifications.add(Utils.getPostBean().getUnreadPosts(this.user));
		notifications.add(Utils.getLotteryBetBean().checkUnreadBetResults(this.user));
		notifications.add(Utils.getFriendshipBean().getNewFriendshipInvites(this.user));
		notifications.add(Utils.getFriendshipBean().getNewFriendshipAcceptances(this.user));
		
		return notifications;
	}
	public void readAllNotifications()
	{
		Utils.getPostBean().readUnreadPosts(this.user);
		Utils.getLotteryBetBean().readUnreadBets(this.user);
		Utils.getFriendshipBean().readUnreadFriendshipAcceptances(this.user);
		Utils.getFriendshipBean().readUnreadFriendshipInvites(this.user);
	}
}
