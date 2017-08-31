import tweepy, time, sys
import datetime
 
#enter the corresponding information from your Twitter application:
CONSUMER_KEY = 'etthXe3rqVJ9lLi5hpUi8Ytbq'#keep the quotes, replace this with your consumer key
CONSUMER_SECRET = 'bCeHLuBkxsNAlOwJLqrvHiSLzDhA6LEGMsnaz94nxD2m6xiTMn'#keep the quotes, replace this with your consumer secret key
ACCESS_KEY = '189151551-z2UK2e6zFMy5EvzHVuCwKoi7i6TOuEyO35XmvFhX'#keep the quotes, replace this with your access token
ACCESS_SECRET = 'fbhsbgSrOBxN9XLmxmeuuayfA7nBef0vtMbPHeYPY72IN'#keep the quotes, replace this with your access token secret
auth = tweepy.OAuthHandler(CONSUMER_KEY, CONSUMER_SECRET)
auth.set_access_token(ACCESS_KEY, ACCESS_SECRET)
api = tweepy.API(auth)
 

for tweet in tweepy.Cursor(api.user_timeline, screen_name="boltiot",since=datetime.datetime.now()).items():
	try:
		tweet.favorite()
		print('Favorited the tweet')
	
		time.sleep(5)

	except tweepy.TweepError as e:
		print(e.reason)

	except StopIteration:
		break
