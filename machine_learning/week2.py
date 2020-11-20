import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.dummy import DummyClassifier
from sklearn.linear_model import LogisticRegression
from sklearn.svm import LinearSVC

df = pd.read_csv("week2.csv", comment='#', header=None)
print(df.head())
X1 = df.iloc[:, 0]
X2 = df.iloc[:, 1]
X = np.column_stack((X1, X2))
y = np.array(df.iloc[:, 2])

# a(i) visualise:
plt.rc('font', size=18)
plt.scatter(X1[y==1], X2[y==1], color='green', marker='o')
plt.scatter(X1[y==-1], X2[y==-1], color='red', marker='o')
plt.title("Actual & Predicted Target Values")
plt.xlabel("x_1")
plt.ylabel("x_2")
# plt.show()

# a(ii) sklearn training:
log_reg = LogisticRegression(penalty='none', solver='lbfgs')
log_reg.fit(X, y)
pred_values = log_reg.predict(X)

plt.scatter(X1[pred_values==1], X2[pred_values==1], color='cyan', marker='+')
plt.scatter(X1[pred_values==-1], X2[pred_values==-1], color='orange', marker='x')
plt.legend(["actual +1", "actual -1", "pred +1", "pred -1"], loc='lower left')
print(f"Intercept={log_reg.intercept_},Coefs={log_reg.coef_}")
print("LogReg Score:", log_reg.score(X, y))

# a(iii) plotting predictions and decision boundary
parameters = np.array(log_reg.coef_).flatten()
print(parameters)
pred = -(log_reg.intercept_ + np.dot(parameters[0], X)) / parameters[1]

plt.plot(X, pred, color='black', linewidth=2)
plt.show()

# b(i) and b(ii) varying C param and plotting each iter
print('\nLinearSVC:')
c_param = [0.001, 0.01, 1, 100, 1000]

for c_val in c_param:
    lin_svc = LinearSVC(C=c_val)
    lin_svc.fit(X, y)
    lin_svc_pred = lin_svc.predict(X)
    parameters = np.array(lin_svc.coef_).flatten()
    pred = - (lin_svc.intercept_ + np.dot(parameters[0], X)) / parameters[1]
    score = lin_svc.score(X, y)
    print(f"C={c_val},Accu={score},Intercept={lin_svc.intercept_},Coefs={lin_svc.coef_}")

    plt.scatter(X1[y==1], X2[y==1], color='green', marker='o')
    plt.scatter(X1[y==-1], X2[y==-1], color='red', marker='o')
    plt.scatter(X1[lin_svc_pred == 1], X2[lin_svc_pred == 1], color='cyan', marker='+')
    plt.scatter(X1[lin_svc_pred == -1], X2[lin_svc_pred == -1], color='orange', marker='x')
    plt.legend(["actual +1", "actual -1", "pred +1", "pred -1"], loc='lower left')
    plt.plot(X, pred, color='black', linewidth=2)
    plt.title(f"LinearSVC with C={c_val}")
    plt.xlabel("x_1")
    plt.ylabel("x_2")
    plt.show()

# c(i) feature engineering
X3 = np.square(X1)
X4 = np.square(X2)
new_X = np.column_stack((X1, X2, X3, X4))

# c(ii)
new_log_reg = LogisticRegression(penalty='none', solver='lbfgs')
new_log_reg.fit(new_X, y)
new_pred = new_log_reg.predict(new_X)
accuracy = new_log_reg.score(new_X, y)
print("Score:", accuracy)

# plotting actual values
plt.scatter(X1[y==1], X2[y==1], color='green', marker='o')
plt.scatter(X1[y==-1], X2[y==-1], color='red', marker='o')
# plotting predicted values
plt.scatter(X1[new_pred==1], X2[new_pred==1], color='cyan', marker='+')
plt.scatter(X1[new_pred==-1], X2[new_pred==-1], color='orange', marker='x')
plt.title("LogRegr w/ squared features")
plt.xlabel("x_1")
plt.ylabel("x_2")
plt.show()
print(new_log_reg.intercept_, new_log_reg.coef_)



# c(iii)
dummy_clf = DummyClassifier(strategy="most_frequent")
dummy_clf.fit(new_X, y)
dummy_clf.predict(new_X)
print("Score dummy:", dummy_clf.score(new_X, y))

# c(iv)
coefs = np.array(new_log_reg.coef_).flatten()
# y = ax^2 + bx + c
