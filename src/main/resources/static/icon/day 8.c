#include<stdio.h>
#include<stdlib.h>
#include<math.h>
typedef int DoubleListElement;
typedef struct DoubleListNode {
    DoubleListElement data;
    struct DoubleListNode* Llink;
    struct DoubleListNode* Rlink;
} DoubleListNode;

//이중 연결 리스트(Doubly Linked List) ADT
DoubleListNode* DoubleInitial(); // 초기화 (헤더 노드 생성)
// 노드 생성
DoubleListNode* DoubleInsertFirst(DoubleListNode* head, DoubleListElement item);
DoubleListNode* DoubleInsertLast(DoubleListNode* head, DoubleListElement item);
// 노드 삭제 
DoubleListNode* DoubleDeleteFirst(DoubleListNode* head);
DoubleListNode* DoubleDeleteLast(DoubleListNode* head);
//리스트 결합 list1 + list2
DoubleListNode* DoubleConcat(DoubleListNode* head1, DoubleListNode* head2);
void DoubleDisplay(DoubleListNode* head); // 리스트 정방향 출력
void DoubleDisplayReverse(DoubleListNode* head); // 리스트 역방향 출력
void FreeDoubleList(DoubleListNode* head); // 리스트 전체 삭제

int main() {
    DoubleListNode* list1 = DoubleInitial();
    DoubleInsertFirst(list1, 10);
    DoubleInsertFirst(list1, 20);
    DoubleInsertFirst(list1, 30);

    DoubleListNode* list1 = DoubleInitial();
    DoubleInsertFirst(list1, 40);
    DoubleInsertFirst(list1, 50);
    DoubleInsertFirst(list1, 60);

    DoubleConcat(list1, list2);
 
    DoubleDisplay(list1);

    DoubleDisplayReverse(list1);
    FreeDoubleList(list1);
}


DoubleListNode* DoubleInitial()
{
    DoubleListNode* header = malloc(sizeof(DoubleListNode));
    header->data = 0;
    header->Llink = header;
    header->Rlink = header;

    return header;
}

DoubleListNode* DoubleInsertFirst(DoubleListNode* head, DoubleListElement item)
{
    if (head == NULL) return NULL;
    //새 노드 만들기
    DoubleListNode* node = malloc(sizeof(DoubleListNode));
    //헤더와 새 노드 연결
    node->data = item;    

    //노드 =  0;
    node->Llink = head;
    node->Rlink = head->Rlink;
    head->Rlink->Llink = node;
    head->Rlink = node;
    head->data++;
    return head;


    //if (head->Rlink == head && head->Llink == head) {
    //    head->Rlink = node;
    //    head->Rlink = node;
    //    node->Rlink = head;
    //    node->Llink = head;
    //}

    ////노드 =  >=1;
    //else /*if (head->Rlink == head->Llink)*/ {
    //    node->Rlink = head->Rlink;
    //    node->Llink = head;
    //    head->Rlink->Llink = node;
    //    head->Rlink = node;

    //}
    //노드>=  2;
 
}

DoubleListNode* DoubleInsertLast(DoubleListNode* head, DoubleListElement item)
{
    if (head == NULL) return NULL;
    DoubleListNode* node = malloc(sizeof(DoubleListNode));
    node->data = item;
    node->Rlink = head;
    node->Llink = head->Llink;
    head->Llink->Rlink = node;
    head->Llink = node;
    head->data++;

    return head;
}

DoubleListNode* DoubleDeleteFirst(DoubleListNode* head)
{
    if (head == NULL) return NULL;
    if (head->Rlink == head)return head;
    DoubleListNode* remove = head->Rlink;
    remove->Llink->Rlink = remove->Rlink;
    remove->Rlink->Llink = remove->Llink;
    free(remove);
    head->data--;

    return head;
}

DoubleListNode* DoubleDeleteLast(DoubleListNode* head)
{
    if (head == NULL) return NULL;
    if (head->Rlink == head)return head;
    DoubleListNode* remove = head->Llink;
    remove->Llink->Rlink = remove->Rlink;
    remove->Rlink->Llink = remove->Llink;
    free(remove);
    head->data--;

    return head;
}

DoubleListNode* DoubleConcat(DoubleListNode* head1, DoubleListNode* head2)
{
    if (head1 == NULL || head2 == NULL)return NULL;
    if (head1->Rlink == head1)return head2;
    if (head2->Rlink == head2)return head1;

    //head1 &head2tail
    DoubleListNode* head2tail = head2->Llink;
    head1->Llink = head2tail;
    head2tail->Rlink = head1;

    //head1tail &head2front
    DoubleListNode* head1tail = head1->Llink;
    DoubleListNode* head2front = head1->Rlink;

    head1tail->Rlink = head2front;
    head2front->Llink = head1tail;
    head2->Rlink = head2->Llink = head2;

   
    return head1;
}

void DoubleDisplay(DoubleListNode* head)
{
    for (DoubleListNode* p = head->Rlink;p != head; p = p->Rlink)
        printf("<- %5d ->", p->data);
    printf("\n\n");
}

void DoubleDisplayReverse(DoubleListNode* head)
{
    for (DoubleListNode* p = head->Llink;p != head; p = p->Llink)
        printf("<- %5d ->", p->data);
    printf("\n\n");
}

void FreeDoubleList(DoubleListNode* head)
{
    if (head == NULL)return;
    while (head->Rlink != head) {
        DoubleListNode* remove = head->Rlink;
        head->Rlink = remove->Rlink;
        free(remove);
    }
    free(head);
}
