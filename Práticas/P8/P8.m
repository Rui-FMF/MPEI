%% Clear
close all;
clear all;
clc;

%% Ex1

%a)
T = [0.2,0.3;0.8,0.7];
Xnf = [0;1]; %X caso não falte
T2 = T^2;

Ra = (T2*Xnf);
resa = Ra(2);

%b)

Xf = [1;0];
Rb = (T2*Xf);
resb = Rb(2);

%c)

T30 = T^30;
Rc = (T30*Xnf);
resc = Rc(2);

%d)

Xd = [0.15;0.85];
probs = zeros(1,30);

for k=1:30
    temp_T = T^(k-1);
    R = (temp_T*Xd);
    probs(k) = R(1);
end

plot(1:30,probs);

%% Ex2

%           A    B    C
%       A  1/3  1/4   0
%       B  1/3  0.55 1/2
%       C  1/3  1/5  1/2        A matriz é estocástica

T = [1/3, 0.25, 0; 1/3, 0.55, 0.5; 1/3, 0.20, 0.50];

turma = [60;15;15];
turma2 = [30;30;30];

c = (T^30)*turma;
d = (T^30)*turma2;

%% Ex3

T = zeros(20,20);

for k = 1 : 20
  r = rand(1, 20);
  r = r / sum(r);
  
  T(:,k) = r';
end

X = zeros(1,20)';
X(1) = 1;

R20 = (T^20)*X;
res20 = R20(20);

R40 = (T^40)*X;
res40 = R40(20);

R100 = (T^100)*X;
res100 = R100(20);


%% Ex4

%               A       B       C       D
%       A      p^2      0       0      q^2
%       B    (1-p)^2    0       0     q(1-q)
%       C     p(1-p)    0       0     q(1-q)
%       D     p(1-p)    1       1    (1-q)^2

p = 0.4;
q = 0.6;

AtoA = p^2;
AtoB = (1-p)^2;
AtoC = p*(1-p);
AtoD = p*(1-p);

DtoA = q^2;
DtoB = q*(1-q);
DtoC = q*(1-q);
DtoD = (1-q)^2;

T = [AtoA, 0, 0, DtoA; AtoB, 0, 0, DtoB; AtoC, 0, 0, DtoC; AtoD 1, 1, DtoD];

X = [1;0;0;0];

res = (T^10)*X;

probA = res(1);
probB = res(2);
probC = res(3);
probD = res(4);



%% Ex5

% S -> Sol      N -> Nuvens     C -> Chuva

T = [0.7, 0.2, 0.3; 0.2, 0.3, 0.3; 0.1, 0.5, 0.4];

%b)

X = [1;0;0];
R = (T^2)*X;
res = R(2);

%c)
n=20;

StoS = zeros(1,n); StoN = zeros(1,n); StoC = zeros(1,n);
NtoS = zeros(1,n); NtoN = zeros(1,n); NtoC = zeros(1,n);
CtoS = zeros(1,n); CtoN = zeros(1,n); CtoC = zeros(1,n);

for k=1:20
      temp_T = T^k;
      StoS(k) = temp_T(1,1);
      StoN(k) = temp_T(2,1);
      StoC(k) = temp_T(3,1);

      NtoS(k) = temp_T(1,2);
      NtoN(k) = temp_T(2,2);
      NtoC(k) = temp_T(3,2);

      CtoS(k) = temp_T(1,3);
      CtoN(k) = temp_T(2,3);
      CtoC(k) = temp_T(3,3);
end

plot(1:n,StoS,'LineWidth',2);
hold on
plot(1:n,NtoS,'LineWidth',2);
plot(1:n,CtoS,'LineWidth',2);

plot(1:n,StoN,'LineWidth',2);
plot(1:n,NtoN,'LineWidth',2);
plot(1:n,CtoN,'LineWidth',2);

plot(1:n,StoC,'LineWidth',2);
plot(1:n,NtoC,'LineWidth',2);
plot(1:n,CtoC,'LineWidth',2);

legend('StoS','NtoS','CtoS','StoN','NtoN','CtoN','StoC','NtoC','CtoC');


%% Ex6

%               1       2       3       4
%       1      0.8      0      0.3      0
%       2      0.2     0.9     0.2      0
%       3       0      0.1     0.4      0
%       4       0       0      0.1      1

%a)
H = [0.8, 0, 0.3, 0; 0.2, 0.9, 0.2, 0; 0, 0.1, 0.4, 0; 0, 0, 0.1, 1];

%b)
X = [1;0;0;0];
Hmil = H^1000;
Res = (Hmil*X);
prob = Res(2);

%c)
H2 = H^2;
H10 = H^10;
H100 = H^100;

%d)
    %               1       2       3    |   4               _____________
    %       1      0.8      0      0.3   |   0              |      |      |
    %       2      0.2     0.9     0.2   |   0          T = |   Q  |  O   |
    %       3       0      0.1     0.4   |   0              |------|------|
    %            -------------------------                  |   R  |  I   |
    %       4       0       0      0.1   |   1              |______|______|

    
Q = H(1:3,1:3);
I = eye(3);

%e)
F = (I-Q)^(-1);
%f)
num_passos = sum(F);
%g)
t = F'*ones(3,1);

%h)
new_H = [0.9, 0.7, 0.6, 0; 0.1, 0.25, 0.2, 0; 0, 0.05, 0.15, 0; 0, 0, 0.05, 1];

new_Q = new_H(1:3,1:3);
new_F = (I-new_Q)^(-1);
new_num_passos = sum(new_F);

%% Ex7

%               A       B       C
%       A      0.8     0.1     0.05
%       B      0.2     0.6     0.2
%       C       0      0.3     0.75

T = [0.8, 0.1, 0.05; 0.2, 0.6, 0.2; 0, 0.3, 0.75];

X = [100;200;30];

%a)
X4 = (T^4)*X;
%b)
Xano = (T^365)*X;

%c)

for k=1:365
    X_temp = (T^k)*X;
    if(X_temp(3)>130)
        dia = k;
        break
    end
end

%Resposta: 9 de Janeiro
