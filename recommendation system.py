

# import libraries

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# mounting google drive

from google.colab import drive

drive.mount('/content/gdrive')

# loading csv file into pandas dataframe

# specify path
my_path = "/content/gdrive/My Drive/Upgrad/ml-latest-small/"

# read ratings file
ratings = pd.read_csv(my_path + 'ratings.csv')

"""#### High-level info of data"""

ratings.head()

ratings.tail()

ratings.shape

ratings.info()

"""There are no null values.

### Train-test split
"""

from sklearn.model_selection import train_test_split

X_train, X_test = train_test_split(ratings, test_size = 0.30, random_state = 42)

print(X_train.shape)
print(X_test.shape)

# pivot ratings into movie features
user_data = X_train.pivot(index = 'userId', columns = 'movieId', values = 'rating').fillna(0)
user_data.head()

"""### Create a Copy of train and test dataset
These datasets will be used for prediction and evaluation.

Dummy train will be used later for prediction of the movies which has not been rated by the user. To ignore the movies rated by the user, we will mark it as 0 during prediction. The movies not rated by user is marked as 1 for prediction.

Dummy test will be used for evaluation. To evaluate, we will only make prediction on the movies rated by the user. So, this is marked as 1. This is just opposite of dummy_train
"""

# make a copy of train and test datasets
dummy_train = X_train.copy()
dummy_test = X_test.copy()

dummy_train['rating'] = dummy_train['rating'].apply(lambda x: 0 if x > 0 else 1)
dummy_test['rating'] = dummy_test['rating'].apply(lambda x: 1 if x > 0 else 0)

# The movies not rated by user is marked as 1 for prediction
dummy_train = dummy_train.pivot(index = 'userId', columns = 'movieId', values = 'rating').fillna(1)

# The movies not rated by user is marked as 0 for evaluation
dummy_test = dummy_test.pivot(index ='userId', columns = 'movieId', values = 'rating').fillna(0)

dummy_train.head()

dummy_test.head()

"""### User-User Similarity matrix

#### Using Cosine similarity
"""

from sklearn.metrics.pairwise import cosine_similarity

# User Similarity Matrix using Cosine similarity as a similarity measure between Users
user_similarity = cosine_similarity(user_data)
user_similarity[np.isnan(user_similarity)] = 0
print(user_similarity)
print(user_similarity.shape)

'''
# using cosine distance
from sklearn.metrics.pairwise import pairwise_distances

# User Similarity Matrix using Cosine similarity as a similarity measure between Users
user_similarity = 1 - pairwise_distances(df_movie_features, metric = 'cosine')
user_similarity[np.isnan(user_similarity)] = 0
print(user_similarity)
print(user_similarity.shape)
'''

"""###  Predicting the User ratings on the movies"""

user_predicted_ratings = np.dot(user_similarity, user_data)
user_predicted_ratings

user_predicted_ratings.shape

"""We do not want to recommend the same movie that the user already watched. We are interested only in the movies not rated by the user, we will ignore the movies rated by the user.

This is where we will use our dummy train matrix that we previously built.
"""

# np.multiply for cell-by-cell multiplication

user_final_ratings = np.multiply(user_predicted_ratings, dummy_train)
user_final_ratings.head()

"""### Top 5 movie recommendations for the User 42"""

user_final_ratings.iloc[42].sort_values(ascending = False)[0:5]

"""## Item-based collaborative filtering"""

movie_features = X_train.pivot(index = 'movieId', columns = 'userId', values = 'rating').fillna(0)
movie_features.head()

"""### Item-Item Similarity matrix

#### Using Cosine similarity
"""

from sklearn.metrics.pairwise import cosine_similarity

# Item Similarity Matrix using Cosine similarity as a similarity measure between Items
item_similarity = cosine_similarity(movie_features)
item_similarity[np.isnan(item_similarity)] = 0
print(item_similarity)
print("- "*10)
print(item_similarity.shape)

"""### Predicting the User ratings on the movies"""

item_predicted_ratings = np.dot(movie_features.T, item_similarity)
item_predicted_ratings

'''
item_predicted_ratings = np.dot(item_similarity, movie_features).T
item_predicted_ratings
'''

"""(A.B)T = (B)T.(A)T"""

item_predicted_ratings.shape

dummy_train.shape

"""#### Filtering the ratings only for the movies not already rated by the user for recommendation"""

# np.multiply for cell-by-cell multiplication

item_final_ratings = np.multiply(item_predicted_ratings, dummy_train)
item_final_ratings.head()

"""### Top 5 movie recommendations for the User 42"""

item_final_ratings.iloc[42].sort_values(ascending = False)[0:5]

"""## Evaluation

Evaluation will we same as you have seen above for the prediction. The only difference being, you will evaluate for the movie already rated by the User instead of predicting it for the movie not rated by the user.

### Using User-User similarity
"""

test_user_features = X_test.pivot(index = 'userId', columns = 'movieId', values = 'rating').fillna(0)
test_user_similarity = cosine_similarity(test_user_features)
test_user_similarity[np.isnan(test_user_similarity)] = 0

print(test_user_similarity)
print("- "*10)
print(test_user_similarity.shape)

user_predicted_ratings_test = np.dot(test_user_similarity, test_user_features)
user_predicted_ratings_test

"""### Testing on the movies already rated by the user"""

test_user_final_rating = np.multiply(user_predicted_ratings_test, dummy_test)
test_user_final_rating.head()

ratings['rating'].describe()

"""But we need to normalize the final rating values between range (0.5, 5)"""

from sklearn.preprocessing import MinMaxScaler

X = test_user_final_rating.copy()
X = X[X > 0] # only consider non-zero values as 0 means the user haven't rated the movies

scaler = MinMaxScaler(feature_range = (0.5, 5))
scaler.fit(X)
pred = scaler.transform(X)

print(pred)

# total non-NaN value
total_non_nan = np.count_nonzero(~np.isnan(pred))
total_non_nan

test = X_test.pivot(index = 'userId', columns = 'movieId', values = 'rating')
test.head()

# RMSE Score

diff_sqr_matrix = (test - pred)**2
sum_of_squares_err = diff_sqr_matrix.sum().sum() # df.sum().sum() by default ignores null values

rmse = np.sqrt(sum_of_squares_err/total_non_nan)
print(rmse)

# Mean abslute error

mae = np.abs(pred - test).sum().sum()/total_non_nan
print(mae)

"""It means that on an average our User-based recommendation engine is making an error of 1.2 in predicting the User ratings.

Now, let's evaluate Item-based recommendation engine.

### Using Item-Item Similarity
"""

test_item_features = X_test.pivot(index = 'movieId', columns = 'userId', values = 'rating').fillna(0)
test_item_similarity = cosine_similarity(test_item_features)
test_item_similarity[np.isnan(test_item_similarity)] = 0

print(test_item_similarity)
print("- "*10)
print(test_item_similarity.shape)

item_predicted_ratings_test = np.dot(test_item_features.T, test_item_similarity )
item_predicted_ratings_test

"""### Testing on the movies already rated by the user"""

test_item_final_rating = np.multiply(item_predicted_ratings_test, dummy_test)
test_item_final_rating.head()

ratings['rating'].describe()

"""But we need to normalize the final rating values between range (0.5, 5)"""

from sklearn.preprocessing import MinMaxScaler

X = test_item_final_rating.copy()
X = X[X > 0] # only consider non-zero values as 0 means the user haven't rated the movies

scaler = MinMaxScaler(feature_range = (0.5, 5))
scaler.fit(X)
pred = scaler.transform(X)

print(pred)

# total non-NaN value
total_non_nan = np.count_nonzero(~np.isnan(pred))
total_non_nan

test = X_test.pivot(index = 'userId', columns = 'movieId', values = 'rating')
test.head()

# RMSE Score

diff_sqr_matrix = (test - pred)**2
sum_of_squares_err = diff_sqr_matrix.sum().sum() # df.sum().sum() by default ignores null values

rmse = np.sqrt(sum_of_squares_err/total_non_nan)
print(rmse)

# Mean abslute error

mae = np.abs(pred - test).sum().sum()/total_non_nan
print(mae)

"""It means that on an average our Item-based recommendation engine is making an error of 2.21 in predicting the User ratings.

## Conclusion

For the give dataset, the User-based collaborative filtering outperformed Item-based collaborative filtering.
"""
