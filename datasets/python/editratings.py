import pandas as pd

names = pd.read_csv('ratings.csv',encoding='utf-8')
names = names[['imdb_title_id','weighted_average_vote','allgenders_0age_avg_vote','allgenders_18age_avg_vote','allgenders_30age_avg_vote','allgenders_45age_avg_vote']]
newnames = names.fillna(0)
print(names.columns.tolist())
newnames.to_csv('newratings.csv',index=False)