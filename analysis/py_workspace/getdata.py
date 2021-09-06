#!/usr/bin/python3
# -*- coding: utf-8 -*-

import datetime
import requests
import pymysql
import re
import time
import urllib3

def running() :
    #日志文件
    logs = "[" + str(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())) + "] Successfully!\n"
    with open('./running.log', 'a', encoding = 'utf-8') as fp:
        fp.write(logs)
    db = pymysql.connect(host = "localhost", port = 3306, user = "root", passwd = "Wuliuqiba12138", db = "jnoj", charset = "utf8")
    cursor = db.cursor()
    sql = "INSERT IGNORE INTO contest_view (`cid`, `title`, `begin_time`, `end_time`, `duration`, `url`, `type`) VALUES ('%s', '%s', %d, %d, '%s', '%s', %d)"
    headers = {
        'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'
    }
    urllib3.disable_warnings()
# Codeforces
    url = "https://codeforces.com/contests"
    contest_list_text = requests.get(url = url, headers = headers).text
    ex = 'data-contestId="(.*?)".*?<td>\r\n(.*?)\r\n.*?"en">(.*?)</span>.*?<td>.*?(\d?\d?:?\d\d:\d\d).*?</td>'
    contest_list = re.findall(ex, contest_list_text, re.S)
    for contest in contest_list :
        contest_url = url + "/" + contest[0]
        begin_time_stamp = int(time.mktime(time.strptime(contest[2], "%b/%d/%Y %H:%M"))) + 18000
        duration = contest[3].split(":")
        if len(duration) == 2 :
            duration_stamp = (int(duration[0]) * 60 + int(duration[1])) * 60
            duration = contest[3]
        else :
            duration_stamp = ((int(duration[0]) * 24 + int(duration[1])) * 60 + int(duration[2])) * 60
            if int(duration[2]) < 10 :
                sec = "0" + str(int(duration[2]))
            else :
                sec = str(int(duration[2]))
            duration = str(int(duration[0]) * 24 + int(duration[1])) + ":" + sec
        end_time_stamp = begin_time_stamp + duration_stamp
        cid = "CF" + contest[0]
        title = contest[1]
        type = 0
        contest_data = (cid, title, begin_time_stamp, end_time_stamp, duration, contest_url, type)
        cursor.execute(sql % contest_data)
        db.commit()
#Atcoder
    url = "https://atcoder.jp/contests"
    contest_list_text = requests.get(url = url, headers = headers).text
    ex = 'full\'>(.*?)\+.*?"/contests/(.*?)">(.*?)<.*?center">(.*?)</td>'
    contest_list = re.findall(ex, contest_list_text, re.S)
    for contest in contest_list :
        contest_url = url + "/" + contest[1]
        begin_time_stamp = int(time.mktime(time.strptime(contest[0], "%Y-%m-%d %H:%M:%S"))) - 3600
        duration = contest[3].split(":")
        duration_stamp = (int(duration[0]) * 60 + int(duration[1])) * 60
        end_time_stamp = begin_time_stamp + duration_stamp
        cid = "AT" + contest[1]
        title = contest[2]
        duration = contest[3]
        type = 1
        contest_data = (cid, title, begin_time_stamp, end_time_stamp, duration, contest_url, type)
        cursor.execute(sql % contest_data)
        db.commit()
#QLUOJ
    url = "https://icpc.qlu.edu.cn/contest"
    contest_list_text = requests.get(url = url, verify = False).text
    ex = 'view\?id=(.*?)">(.*?)</a>.*?<td>.*?<td>(.*?)</td><td>(.*?)</td>'
    contest_list = re.findall(ex, contest_list_text, re.S)
    for contest in contest_list :
        contest_url = url + "/view?id=" + contest[0]
        cid = "QLU" + contest[0]
        title = contest[1]
        begin_time_stamp = int(time.mktime(time.strptime(contest[2], "%Y-%m-%d %H:%M:%S")))
        end_time_stamp = int(time.mktime(time.strptime(contest[3], "%Y-%m-%d %H:%M:%S")))
        duration_stamp = end_time_stamp - begin_time_stamp
        duration_h = duration_stamp // 3600
        duration_m = duration_stamp % 3600 // 60
        if duration_h < 10 :
            str_duration_h = "0" + str(duration_h)
        else :
            str_duration_h = str(duration_h)
        if duration_m < 10 :
            str_duration_m = "0" + str(duration_m)
        else :
            str_duration_m = str(duration_m)
        duration = str_duration_h + ":" + str_duration_m
        type = 2
        contest_data = (cid, title, begin_time_stamp, end_time_stamp, duration, contest_url, type)
        cursor.execute(sql % contest_data)
        db.commit()
def main() :
    while True :
        nowtime = datetime.datetime.now()
        if nowtime.minute == 0 : #整点爬取
            break
    running()

print ("[" + str(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())) + "] Create Task Successfully!")

while True:
    main()
    time.sleep(3540)

