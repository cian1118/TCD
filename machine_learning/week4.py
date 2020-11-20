import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.dummy import DummyClassifier
from sklearn.preprocessing import PolynomialFeatures
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import KFold
from sklearn.metrics import mean_squared_error
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import confusion_matrix
from sklearn.metrics import roc_curve
from sklearn.metrics import auc
from sklearn.model_selection import train_test_split


# read in data (choose dataset by commenting)
# if changing dataset also change line 184/185!
# df = pd.read_csv("week4_1.csv", comment='#', header=None)
df = pd.read_csv("week4_2.csv", comment='#', header=None)
print(df.head())
X1 = df.iloc[:, 0]
X2 = df.iloc[:, 1]
X = np.column_stack((X1, X2))
y = np.array(df.iloc[:, 2])


def do_2d_plot(x, pred, title):
    plt.rc('font', size=14)
    plt.scatter(x[:, 0][y==1], x[:, 1][y==1], color='green', marker='o', label='target: +1')
    plt.scatter(x[:, 0][y==-1], x[:, 1][y==-1], color='blue', marker='o', label='target: -1')
    if (y-pred).any():
        plt.scatter(x[:, 0][pred==1], x[:, 1][pred==1], color='yellow', marker='+', label='prediction: +1')
        plt.scatter(x[:, 0][pred==-1], x[:, 1][pred==-1], color='red', marker='+', label='prediction: -1')
    plt.title(title)
    plt.xlabel('x_1')
    plt.ylabel('x_2')
    plt.legend(loc='lower left')
    plt.tight_layout()
    plt.show()


def do_error_plot(x, means, yerr, title, x_label):
    plt.errorbar(x, means, yerr=yerr, fmt='.', capsize=5)
    plt.plot(x, means, linestyle=':', label='mean', linewidth=2, color='orange')
    plt.legend()
    plt.title(title)
    plt.xlabel(x_label)
    plt.ylabel('mean square error')
    plt.tight_layout()
    plt.show()


def make_poly_features(x, degree):
    poly = PolynomialFeatures(degree=degree)
    new_x = poly.fit_transform(x)
    return new_x


def k_fold_cross_val(k, model, input):
    k_fold = KFold(n_splits=k)
    sq_errs = []
    for train, test in k_fold.split(input):
        curr_model = model.fit(input[train], y[train])
        pred = curr_model.predict(input[test])
        sq_err = mean_squared_error(y[test], pred)
        sq_errs.append(sq_err)
    mean = np.mean(sq_errs)
    std = np.std(sq_errs)
    return mean, std


do_2d_plot(X, y, 'Data & Target Values')

""" (i)(a) i. polynomial features and logistic regression with L2 penalty """
cc = [0.001, 1, 1000]
qq = [1, 2, 3, 4, 5, 6]
for c in cc:
    mm = []
    sd = []
    for q in qq:
        poly = make_poly_features(X, q)
        log_clf = LogisticRegression(penalty='l2', C=c, max_iter=100000)
        log_clf.fit(poly, y)
        results = k_fold_cross_val(5, log_clf, poly)
        mm.append(results[0])
        sd.append(results[1])
    plt.errorbar(qq, mm, yerr=sd, fmt='.', capsize=5, label=c)
    plt.plot(qq, mm, linestyle=':', label=c, linewidth=2)
plt.ylabel('mean square error')
plt.title('Prediction Error: varying q and C parameters')
plt.xlabel('degree q')
plt.legend()
plt.show()

kk = [2, 3, 8]
qq = [1, 2, 4, 6]
for k in kk:
    mm = []
    sd = []
    for q in qq:
        poly = make_poly_features(X, q)
        log_clf = KNeighborsClassifier(n_neighbors=k)
        log_clf.fit(poly, y)
        results = k_fold_cross_val(5, log_clf, poly)
        mm.append(results[0])
        sd.append(results[1])
    plt.errorbar(qq, mm, yerr=sd, fmt='.', capsize=5, label=k)
    plt.plot(qq, mm, linestyle=':', label=k, linewidth=2)
plt.ylabel('mean square error')
plt.title('Prediction Error: varying q and k parameters')
plt.xlabel('degree q')
plt.legend(title='k values')
plt.show()

q_means = []
q_std_devs = []
degrees = [1, 2, 3, 4, 5, 6]
for deg in degrees:
    polyX = make_poly_features(X, deg)
    l2_log_reg = LogisticRegression(penalty='l2', C=1)
    l2_log_reg.fit(polyX, y)
    pred = l2_log_reg.predict(polyX)
    print(f'C=1, degree={deg}, accuracy={l2_log_reg.score(polyX, y)}')
    results = k_fold_cross_val(5, l2_log_reg, polyX)
    q_means.append(results[0])
    q_std_devs.append(results[1])
    do_2d_plot(X, pred, f'Logistic Regression with C=1, degree q={deg}')
do_error_plot(degrees, q_means, q_std_devs, 'Logistic Regression C=1, Cross Validation for q', 'q: polynomial degree')

""" (i)(a) ii. C parameter and logistic regression with L2 penalty """
C_means = []
C_std_devs = []
C_values = [0.001, 0.01, 0.1, 1, 10, 100, 1000]
polyX = make_poly_features(X, 2)
for C_value in C_values:
    l2_log_reg = LogisticRegression(penalty='l2', C=C_value)
    l2_log_reg.fit(polyX, y)
    print(f'C={C_value}, degree=2, accuracy={l2_log_reg.score(polyX, y)}')
    results = k_fold_cross_val(5, l2_log_reg, polyX)
    C_means.append(results[0])
    C_std_devs.append(results[1])
    y_pred = l2_log_reg.predict(polyX)
    do_2d_plot(X, y_pred, f'Logistic Regression with C={C_value}, degree q=2')
do_error_plot(np.log10(C_values), C_means, C_std_devs, 'Logistic Regression Cross Validation for C, q=2', 'log10(C)')

""" (i)(b) kNN classifier """
knn_means = []
knn_std_devs = []
knn_poly_means = []
knn_poly_std_devs = []

k_vals = [1, 2, 3, 4, 5, 6, 7]
for k in k_vals:
    knn = KNeighborsClassifier(n_neighbors=k)
    knn.fit(X, y)
    pred = knn.predict(X)
    print(f'kNN, k={k}, accuracy={knn.score(X, y)}')
    results = k_fold_cross_val(5, knn, X)
    knn_means.append(results[0])
    knn_std_devs.append(results[1])
    do_2d_plot(X, pred, f'kNN k={k}')

    knn.fit(polyX, y)
    knn_poly_pred = knn.predict(polyX)
    print(f'kNN-poly, k={k}, accuracy={knn.score(polyX, y)}')
    poly_results = k_fold_cross_val(5, knn, polyX)
    knn_poly_means.append(results[0])
    knn_poly_std_devs.append(results[1])
    do_2d_plot(X, knn_poly_pred, f'kNN poly k={k}')

do_error_plot(k_vals, knn_means, knn_std_devs, '5-fold Cross Validation kNN', 'k neighbours')
do_error_plot(k_vals, knn_poly_means, knn_poly_std_devs, '5-fold Cross Validation kNN w/ poly features', 'k neighbours')


""" (i)(c) confusion matrices """
models = []
""" final logistic regression classifier, q=2, C=1 """
log_reg_clf = LogisticRegression(C=1, penalty='l2')
log_reg_clf.fit(polyX, y)
log_reg_pred = log_reg_clf.predict(polyX)
print('log_reg\n', confusion_matrix(y, log_reg_pred))  # tn, fp, tp, fn
models.append(log_reg_clf)

""" final knn classifier, k=2, NOT poly """
# knn_clf = KNeighborsClassifier(n_neighbors=2)  # dataset1
knn_clf = KNeighborsClassifier(n_neighbors=3)  # dataset2
knn_clf.fit(X, y)
knn_pred = knn_clf.predict(X)
print('knn\n', confusion_matrix(y, knn_pred))
models.append(knn_clf)

""" most frequent dummy classifier """
most_freq_clf = DummyClassifier(strategy='most_frequent')
most_freq_clf.fit(polyX, y)
most_freq_pred = most_freq_clf.predict(polyX)
most_freq_conf_mat = confusion_matrix(y, most_freq_pred)
print('most_freq\n', most_freq_conf_mat)
models.append(most_freq_clf)

""" random classifier """
rand_clf = DummyClassifier(strategy='uniform')
rand_clf.fit(polyX, y)
rand_pred = rand_clf.predict(polyX)
rand_conf_mat = confusion_matrix(y, rand_pred)
print('rand\n', rand_conf_mat)
models.append(rand_conf_mat)

""" (i)(d) ROC curves """
Xtrain, Xtest, ytrain, ytest = train_test_split(polyX, y, test_size=0.2)  # polynomial features for LogReg
for model in models[:2]:
    model.fit(Xtrain, ytrain)
    scores = model.predict_proba(Xtest)
    fpr, tpr, _ = roc_curve(ytest, scores[:, 1])
    print(auc(fpr, tpr))
    model_name = type(model).__name__
    if model_name == 'LogisticRegression':
        model_name = 'LogisticRegression q=2, C=1'
    else:
        model_name = 'kNN Classifier k=3'
    plt.plot(fpr, tpr, label=model_name)
    Xtrain, Xtest, ytrain, ytest = train_test_split(X, y, test_size=0.2)  # switch to normal features for kNN iteration

""" plotting points of the baseline clfs: rand and most_freq """
rand_fpr = rand_conf_mat[0][1] / (rand_conf_mat[0][1] + rand_conf_mat[0][0])  # FP / (FP + TN)
rand_tpr = rand_conf_mat[1][1] / (rand_conf_mat[1][1] + rand_conf_mat[1][0])  # TP / (TP + FN)
most_freq_fpr = most_freq_conf_mat[0][1] / (most_freq_conf_mat[0][1] + most_freq_conf_mat[0][0])  # FP / (FP + TN)
most_freq_tpr = most_freq_conf_mat[1][1] / (most_freq_conf_mat[1][1] + most_freq_conf_mat[1][0])  # TP / (TP + FN)

plt.plot(rand_fpr, rand_tpr, label='Random Classifier', marker='o', linestyle='None')
plt.plot(most_freq_fpr, most_freq_tpr, label='Most Frequent Clf.', marker='o', linestyle='None')
plt.xlabel('False positive rate')
plt.ylabel('True positive rate')
plt.plot([0, 1], [0, 1], color='green', linestyle='--')
plt.title('ROC curves for the chosen classifiers')
plt.legend()
plt.show()
